package products

import BankMediator
import DepositTransfer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm1
import interestMechanisms.InterestAlgorithm3
import org.junit.jupiter.api.BeforeEach
import reporting.ReportVisitor
import java.time.LocalDate
import java.time.Period

class DepositTest {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var deposit: Deposit
    private lateinit var mediator: BankMediator

    @BeforeEach
    fun setUp() {
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm3())
        account.balance = 1000.0
        deposit = Deposit(account, 0.0, Period.ofMonths(12), customer, LocalDate.now(), 100.0, InterestAlgorithm1(), account.bank)
        account.associatedProducts["deposits"]?.add(deposit)
    }

    @Test
    fun getAssociatedAccount() {
        val associatedAccount = deposit.getAssociatedAccount()
        assertEquals(account, associatedAccount)
    }

    @Test
    fun transferEarlyWithdrawalWithInterestLoss() {
        bank.executeCommand(DepositTransfer(deposit))
        assertEquals(0.0, deposit.calculatedInterest)
        assertEquals(1300.0, account.balance)
    }

    @Test
    fun close() {
        deposit.close()
        assertEquals(0.0, deposit.balance)
        assertEquals(0.0, deposit.calculatedInterest)
        assertFalse(account.associatedProducts["deposits"]?.contains(deposit) ?: false)
    }

    @Test
    fun open() {
        deposit.open()
        assertTrue(account.associatedProducts["deposits"]?.contains(deposit) ?: false)
    }

    @Test
    fun accept() {
        var visitDepositCalled = false
        val visitor = object : ReportVisitor {
            override fun visit(customer: Customer) {
                kotlin.test.fail("Unexpected visit to customer")
            }

            override fun visit(account: Account) {
                kotlin.test.fail("Unexpected visit to account")
            }

            override fun visit(deposit: Deposit) {
                visitDepositCalled = true
                assertEquals(account, deposit.getAssociatedAccount())
                assertEquals(0.0, deposit.calculatedInterest)
                assertEquals(Period.ofMonths(6), deposit.period)
                assertEquals(customer, deposit.getOwner())
                assertEquals(LocalDate.now(), deposit.getDateOpened())
                assertEquals(500.0, deposit.balance)
                assertTrue(deposit.getInterestMechanism() is InterestAlgorithm1)
                assertEquals(bank, deposit.bank)
            }

            override fun visit(loan: Loan) {
                kotlin.test.fail("Unexpected visit to loan")
            }

            override fun generateReport(): String {
                kotlin.test.fail("Unexpected call to generateReport")
            }
        }
        deposit.accept(visitor)
        assertTrue(visitDepositCalled)
    }

    @Test
    fun getCalculatedInterest() {
        val calculatedInterest = deposit.calculatedInterest

        assertEquals(50.0, calculatedInterest)
    }

    @Test
    fun setCalculatedInterest() {
        deposit.calculatedInterest = 60.0
        assertEquals(60.0, deposit.calculatedInterest)
    }
}
