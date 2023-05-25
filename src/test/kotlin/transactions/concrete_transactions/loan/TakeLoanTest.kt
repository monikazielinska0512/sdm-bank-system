package transactions.concrete_transactions.loan

import BankMediator
import InterBankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm3
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import products.Loan
import transactions.TransactionType
import java.time.Period

class TakeLoanTest {

    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var takeLoanTransaction: TakeLoan
    private lateinit var mediator: BankMediator

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm3())
        account.balance = 1000.0
        takeLoanTransaction = TakeLoan(account, 500.0, Period.ofMonths(12))
    }

    @Test
    fun testExecute() {
        // Execute the transaction
        takeLoanTransaction.execute()

        // Assert the transaction type
        assertEquals(TransactionType.TAKE_LOAN, takeLoanTransaction.type)

        // Assert the transaction description
        val expectedDescription = "Loan was taken. Loan_value: 500.0"
        assertEquals(expectedDescription, takeLoanTransaction.description)

        // Assert the transaction is added to the loan's transaction history
        val loan = takeLoanTransaction.product as Loan
        assertEquals(1, loan.getTransactionHistory().getHistory().size)
        assertEquals(takeLoanTransaction, loan.getTransactionHistory().getHistory()[0])

        // Assert the transaction is added to the associated account's transaction history
        assertEquals(2, account.getTransactionHistory().getHistory().size)
        assertEquals(takeLoanTransaction, account.getTransactionHistory().getHistory()[1])
    }
}