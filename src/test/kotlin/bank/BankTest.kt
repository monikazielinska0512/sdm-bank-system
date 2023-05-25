import bank.Bank
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import products.Deposit
import transactions.concrete_transactions.deposit.OpenDeposit
import java.time.Period

class BankTest {

    private lateinit var bank: Bank
    private lateinit var mediator: BankMediator

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
    }

    @Test
    fun testCreateAccount() {
        val customer = bank.createCustomer("John", "Doe")
        val interestMechanism = InterestAlgorithm2()

        val account = bank.createAccount(customer, interestMechanism)

        Assertions.assertNotNull(account)
        Assertions.assertEquals(customer, account.getOwner())
        Assertions.assertEquals(bank, account.bank)
        Assertions.assertEquals(0.0, account.balance)
    }

    @Test
    fun testCreateCustomer() {
        val customer = bank.createCustomer("John", "Doe")

        Assertions.assertNotNull(customer)
        Assertions.assertEquals("John", customer.firstName)
        Assertions.assertEquals("Doe", customer.lastName)
        Assertions.assertEquals(bank, customer.bank)
    }

    @Test
    fun testExecuteCommand() {
        val customer = bank.createCustomer("John", "Doe")
        val interestMechanism = InterestAlgorithm2()
        val account = bank.createAccount(customer, interestMechanism)

        val initialBalance = account.balance
        val period = Period.ofMonths(12)

        val openDepositTransaction = OpenDeposit(account, period, interestMechanism)

        bank.executeCommand(openDepositTransaction)

        Assertions.assertTrue(account.associatedProducts["deposits"]?.size == 1)
        Assertions.assertEquals(initialBalance, account.associatedProducts["deposits"]?.get(0)?.balance)
        Assertions.assertTrue(bank.getTransactionHistory().getHistory().contains(openDepositTransaction))
    }

}