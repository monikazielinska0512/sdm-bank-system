import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import products.Account
import products.Loan
import transactions.concrete_transactions.loan.TakeLoan
import java.time.LocalDate
import java.time.Period

class LoanTest {

    private lateinit var loan: Loan
    private lateinit var account: Account
    private lateinit var bank: Bank
    private lateinit var mediator: BankMediator

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        val owner = Customer("John", "Doe", Bank("MyBank", mediator))
        account = Account(owner, LocalDate.now(), 10000.0, InterestAlgorithm2(), Bank("MyBank", mediator))
        val value = 1000.0
        val period = Period.ofYears(1)
        val dateOpened = LocalDate.now()
        val interestMechanism = InterestAlgorithm2()
        bank = Bank("MyBank", mediator)
        loan = Loan(owner, account, value, period, dateOpened, interestMechanism, bank)
    }

    @Test
    fun testOpen() {
        val account = Account(Customer("Jane", "Smith", bank), LocalDate.now(), 0.0,  InterestAlgorithm2(), bank)
        val amount = 500.0
        loan.open(account, amount)
        Assertions.assertEquals(account, loan.getAssociatedAccount())
        Assertions.assertEquals(amount, loan.getAssociatedAccount().balance)
        Assertions.assertTrue(account.associatedProducts["loans"]?.contains(loan) == true)
        Assertions.assertTrue(bank.entities.contains(loan))
    }

    @Test
    fun testPayRate() {
        val paymentAmount = 100.0
        val paymentAmount2 = 2000.0
        // Test when the payment does not cover the rest of the repaymentValue
        loan.payRate(paymentAmount)
        Assertions.assertEquals(1000.0, loan.getRepaymentValue())
        Assertions.assertEquals(loan.getAssociatedAccount().balance, 9900.0)

        // Test when the payment covers the rest of the repaymentValue
        loan.payRate(paymentAmount2)
        Assertions.assertTrue(loan.getIsPaidOff())
        Assertions.assertEquals(loan.getRepaymentValue(), 0.0)
        Assertions.assertEquals(loan.getAssociatedAccount().balance, 8900.0)
//        Assertions.assertTrue(loan.getAssociatedAccount().associatedProducts["loans"]?.contains(loan))
    }

}