package transactions.concrete_transactions.account
import InterBankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import products.Account
import transactions.TransactionType
import java.time.LocalDate

class OpenAccountTest {
    private lateinit var account: Account
    private lateinit var openAccountTransaction: OpenAccount
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var mediator: InterBankPaymentAgency

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank =  Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = Account(customer, LocalDate.now(), 0.0, InterestAlgorithm2(), bank)
        openAccountTransaction = OpenAccount(account)
    }

    @Test
    fun testExecute() {
        // Execute the transaction
        openAccountTransaction.execute()

        // Assert the transaction type
        assertEquals(TransactionType.OPEN_ACCOUNT, openAccountTransaction.type)

        // Assert the account state after execution
        assertEquals(true, account.getIsActive())
        assertEquals(0.0, account.balance, 0.001)
        assertEquals(false, account.getIsDebit())

        // Assert the transaction description
        val expectedDescription = "Account was opened. Balance: 0.0, isDebit: false"
        assertEquals(expectedDescription, openAccountTransaction.description)

        // Assert the transaction is added to the account's transaction history
        assertEquals(1, account.getTransactionHistory().size())
        assertEquals(openAccountTransaction, account.getTransactionHistory().getHistory()[0])
    }
}