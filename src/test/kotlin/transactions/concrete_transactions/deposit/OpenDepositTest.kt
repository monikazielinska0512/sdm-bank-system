package transactions.concrete_transactions.deposit

import InterbankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm1
import interestMechanisms.InterestAlgorithm3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import products.Account
import products.Deposit
import transactions.TransactionType
import transactions.concrete_transactions.deposit.CloseDeposit
import java.time.LocalDate
import java.time.Period

class OpenDepositTest {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var deposit: Deposit
    private lateinit var openDepositTransaction: OpenDeposit
    @BeforeEach
    fun setUp() {
        bank = Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm3())
        account.balance = 1000.0
        openDepositTransaction = OpenDeposit(account, Period.ofMonths(12), InterestAlgorithm1())
    }

    @Test
    fun testExecute() {
        // Execute the transaction
        openDepositTransaction.execute()
        deposit = account.associatedProducts["deposits"]?.get(0) as Deposit

        // Assert the transaction type
        assertEquals(TransactionType.OPEN_DEPOSIT, openDepositTransaction.type)

        // Assert the transaction description
        val expectedDescription = "Deposit was opened. Deposit_balance: ${deposit.balance}"
        assertEquals(expectedDescription, openDepositTransaction.description)

        // Assert the transaction is added to the deposit's transaction history
        assertEquals(1, deposit.getTransactionHistory().getHistory().size)
        assertEquals(openDepositTransaction, deposit.getTransactionHistory().getHistory()[0])

        // Assert the transaction is added to the associated account's transaction history
        assertEquals(2, deposit.getAssociatedAccount().getTransactionHistory().getHistory().size)
        assertEquals(openDepositTransaction, deposit.getAssociatedAccount().getTransactionHistory().getHistory()[1])
    }
}