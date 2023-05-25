import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import products.Account
import java.time.LocalDate

class AccountTest {

    private lateinit var account1: Account
    private lateinit var account2: Account
    private lateinit var bank1: Bank
    private lateinit var bank2: Bank
    private lateinit var customer1: Customer
    private lateinit var customer2: Customer
    private lateinit var mediator: BankMediator

    @BeforeEach
    fun setUp() {
        bank1 = Bank("MyBank1", mediator)
        bank2 = Bank("MyBank2", mediator)
        customer1 = Customer("John", "Doe", bank1)
        customer2 = Customer("Doe", "John", bank1)
        val openingDate = LocalDate.now()
        val balance = 1000.0
        val interestMechanism = InterestAlgorithm2()

        account1 = Account(customer1, openingDate, balance, interestMechanism, bank1)
        account2 = Account(customer2, openingDate, 0.0, interestMechanism, bank2)
    }

    @Test
    fun testSwitchToDebit() {
        val limit = 500.0
        account1.switchToDebit(limit)

        Assertions.assertTrue(account1.getIsDebit())
        Assertions.assertEquals(limit, account1.getDebitLimit())
    }

    @Test
    fun testTransfer() {
        val receiver = account2
        val transferAmount = 500.0

        account1.transfer(receiver, transferAmount)
        Assertions.assertEquals(500.0, account1.balance)
        Assertions.assertEquals(transferAmount, receiver.balance)
    }

    @Test
    fun testWithdrawMoney() {
        val withdrawalAmount = 500.0

        account1.withdrawMoney(withdrawalAmount)

        Assertions.assertEquals(500.0, account1.balance)
    }

    @Test
    fun testClose() {
        account1.close()
        Assertions.assertFalse(account1.getIsActive())
        Assertions.assertNotNull(account1.getClosedDate())
        Assertions.assertEquals(LocalDate.now(), account1.getClosedDate())
    }

    @Test
    fun testOpen() {
        account1.open()
        Assertions.assertTrue(bank1.entities.contains(account1))
    }

}