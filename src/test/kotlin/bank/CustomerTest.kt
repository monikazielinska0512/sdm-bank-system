package bank

import BankMediator
import InterBankPaymentAgency
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import products.Deposit
import products.Loan
import reporting.ReportVisitor
import kotlin.test.fail

class CustomerTest {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var mediator: BankMediator
    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
    }


    @Test
    fun accept() {
        var visitCustomerCalled = false

        val visitor = object : ReportVisitor {
            override fun visit(customer: Customer) {
                visitCustomerCalled = true
                // Perform necessary assertions or actions
                assertEquals("John", customer.firstName)
                assertEquals("Doe", customer.lastName)
                assertEquals(bank, customer.bank)
            }

            override fun visit(account: Account) {
                fail("Unexpected visit to account")
            }

            override fun visit(deposit: Deposit) {
                fail("Unexpected visit to deposit")
            }

            override fun visit(loan: Loan) {
                fail("Unexpected visit to loan")
            }

            override fun generateReport(): String {
                fail("Unexpected call to generateReport")
            }
        }
        customer.accept(visitor)
        assertTrue(visitCustomerCalled)
    }

    @Test
    fun addToBank() {
        customer.addToBank()
        assertTrue(bank.entities.contains(customer))
    }

    @Test
    fun removeFromBank() {
        bank.entities.add(customer)
        customer.removeFromBank()
        assertFalse(bank.entities.contains(customer))
    }
}
