package products

import BankMediator
import DepositTransfer
import InterBankPaymentAgency
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm1
import interestMechanisms.InterestAlgorithm3
import org.junit.jupiter.api.BeforeEach
import reporting.ReportVisitor
import transactions.concrete_transactions.deposit.CloseDeposit
import transactions.concrete_transactions.product.Transfer
import java.time.LocalDate
import java.time.Period

class DepositTest {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var deposit: Deposit
    private lateinit var mediator: InterBankPaymentAgency

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
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
        deposit.open()
        deposit.addMoney(1000.0)
        deposit.transfer(deposit.getAssociatedAccount(), deposit.balance)
        assertEquals( 2050.0, account.balance + deposit.balance)
        assertEquals(0.0, deposit.balance)
        assertEquals(0.0, deposit.calculatedInterest)
    }

    @Test
    fun close() {
        deposit.open()
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
    fun getCalculatedInterest() {
        deposit.addMoney(1000.0)
        val calculatedInterest = deposit.calculateInterest()
        assertEquals(50.0, calculatedInterest)
    }

    @Test
    fun setCalculatedInterest() {
        deposit.calculatedInterest = 60.0
        assertEquals(60.0, deposit.calculatedInterest)
    }
}
