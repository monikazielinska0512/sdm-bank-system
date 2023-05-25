import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import products.Account
import reporting.AccountReportVisitor
import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AccountReportVisitorTest {
    private lateinit var accountReportVisitor: AccountReportVisitor
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account1: Account
    private lateinit var account2: Account

    @BeforeEach
    fun setUp() {
        bank =  Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        accountReportVisitor = AccountReportVisitor()
        account1 = Account(customer, LocalDate.now(), 10000.0, InterestAlgorithm2(), bank)
        account2 = Account(customer, LocalDate.now(), 20000.0, InterestAlgorithm2(), bank)
    }

    @Test
    fun testVisit() {
        accountReportVisitor.visit(account1)
        accountReportVisitor.visit(account2)

        assertEquals(2, accountReportVisitor.getAccountList().size)
        assertTrue(accountReportVisitor.getAccountList().contains(account1))
        assertTrue(accountReportVisitor.getAccountList().contains(account2))
    }

//    @Test
//    fun testGenerateReport() {
//        // Add accounts to the visitor
//        account1.accept(accountReportVisitor)
//        account2.accept(accountReportVisitor)
//
//        // Generate the report
//        val report = accountReportVisitor.generateReport()
//
//        // Expected report
//        val expectedReport = """
//            |///Account List Report///
//            |+----------------------------------------+-----------------------------+--------------------+----------------+
//            || Account ID                            | Owner                        | Balance            | Date Opened    |
//            |+----------------------------------------+-----------------------------+--------------------+----------------+
//            || 001                                  | John Doe                     | 1000.0             | ${LocalDate.now()} |
//            || 002                                  | Jane Smith                   | 2000.0             | ${LocalDate.now()} |
//            |+----------------------------------------+-----------------------------+--------------------+----------------+
//            """
//
//        // Compare the generated report with the expected report
//        Assertions.assertEquals(expectedReport, report)
//    }
}