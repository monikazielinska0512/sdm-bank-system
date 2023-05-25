package reporting

import InterbankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm1
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import products.Deposit
import java.time.LocalDate
import java.time.Period

class DepositReportVisitorTest {

    private lateinit var depositReportVisitor: DepositReportVisitor
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account1: Account
    private lateinit var account2: Account
    private lateinit var deposit1: Deposit
    private lateinit var deposit2: Deposit


    @BeforeEach
    fun setUp() {
        bank =  Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        depositReportVisitor = DepositReportVisitor()
        account1 = Account(customer, LocalDate.now(), 10000.0, InterestAlgorithm2(), bank)
        account2 = Account(customer, LocalDate.now(), 20000.0, InterestAlgorithm2(), bank)

        account1.balance = 1000.0
        deposit1 = Deposit(account1, 0.0, Period.ofMonths(12), customer, LocalDate.now(), 100.0, InterestAlgorithm1(), bank)
        account1.associatedProducts["deposits"]?.add(deposit1)

        account2.balance = 1000.0
        deposit2 = Deposit(account2, 0.0, Period.ofMonths(6), customer, LocalDate.now(), 500.0, InterestAlgorithm1(), bank)
        account2.associatedProducts["deposits"]?.add(deposit2)
    }

    @Test
    fun testVisit() {
        depositReportVisitor.visit(deposit1)
        depositReportVisitor.visit(deposit2)

        assertEquals(2, depositReportVisitor.getDepositList().size)
        assertTrue(depositReportVisitor.getDepositList().contains(deposit1))
        assertTrue(depositReportVisitor.getDepositList().contains(deposit2))
    }
}