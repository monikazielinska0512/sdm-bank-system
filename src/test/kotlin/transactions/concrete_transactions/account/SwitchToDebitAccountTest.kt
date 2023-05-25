package transactions.concrete_transactions.account

import InterBankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import transactions.TransactionType.*
import java.time.LocalDate

class SwitchToDebitAccountTest {

    private lateinit var account: Account
    private lateinit var switchToDebitAccount: SwitchToDebitAccount
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var mediator: InterBankPaymentAgency

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank =  Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = Account(customer, LocalDate.now(), 0.0, InterestAlgorithm2(), bank)
        switchToDebitAccount = SwitchToDebitAccount(account, 100.0)
    }

    @Test
    fun testExecute() {
        // Execute the transaction
        switchToDebitAccount.execute()

        // Assert the transaction type
        assertEquals(DEBIT, switchToDebitAccount.type)

        // Assert the account state after execution
        assertEquals(true, account.getIsDebit())
        assertEquals(100.0, account.getDebitLimit(), 0.001)

        // Assert the transaction description
        val expectedDescription = "Debit is turned on. Balance: 0.0"
        assertEquals(expectedDescription, switchToDebitAccount.description)

        // Assert the transaction is added to the account's transaction history
        assertEquals(1, account.getTransactionHistory().size())
        assertEquals(switchToDebitAccount, account.getTransactionHistory().getHistory()[0])
    }
}