import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.BeforeEach
import products.Account
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class WithdrawalTest {
    private lateinit var bank: Bank
    private lateinit var mediator: BankMediator
    private lateinit var customer: Customer
    private lateinit var account: Account

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = Account(customer, LocalDate.now(), 100.0, InterestAlgorithm2(), bank)
    }

    @Test
    fun execute() {
        val initialBalance = account.balance
        val amount = 50.0
        val withdrawal = Withdrawal(account, amount)
        withdrawal.execute()
        assertEquals(initialBalance - amount, account.balance, 0.01)
    }
}
