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
import products.Loan
import java.time.LocalDate
import java.time.Period

class LoanReportVisitorTest {

    private lateinit var loanReportVisitor: LoanReportVisitor
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account1: Account
    private lateinit var account2: Account
    private lateinit var loan1: Loan
    private lateinit var loan2: Loan


    @BeforeEach
    fun setUp() {
        bank =  Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        loanReportVisitor = LoanReportVisitor()
        account1 = Account(customer, LocalDate.now(), 10000.0, InterestAlgorithm2(), bank)
        account2 = Account(customer, LocalDate.now(), 20000.0, InterestAlgorithm2(), bank)

        account1.balance = 1000.0
        loan1 = Loan(customer, account1, 1000.0, Period.ofMonths(12), LocalDate.now(), InterestAlgorithm1(), bank)
        account1.associatedProducts["loans"]?.add(loan1)

        account2.balance = 1000.0
        loan2 = Loan(customer, account2, 500.0, Period.ofMonths(6), LocalDate.now(), InterestAlgorithm2(), bank)
        account2.associatedProducts["loans"]?.add(loan2)
    }

    @Test
    fun testVisit() {
        loanReportVisitor.visit(loan1)
        loanReportVisitor.visit(loan2)

        assertEquals(2, loanReportVisitor.getLoanList().size)
        assertTrue(loanReportVisitor.getLoanList().contains(loan1))
        assertTrue(loanReportVisitor.getLoanList().contains(loan2))
    }
}