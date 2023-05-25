package reporting

import InterbankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import java.time.LocalDate

class CustomerReportVisitorTest {

    private lateinit var customerReportVisitor: CustomerReportVisitor
    private lateinit var bank: Bank
    private lateinit var customer1: Customer
    private lateinit var customer2: Customer

    @BeforeEach
    fun setUp() {
        bank =  Bank("MyBank", InterbankPaymentAgency())
        customerReportVisitor = CustomerReportVisitor()
        customer1 = Customer("John", "Doe", bank)
        customer2 = Customer("Doe", "John", bank)
    }

    @Test
    fun testVisit() {
        customerReportVisitor.visit(customer1)
        customerReportVisitor.visit(customer2)

        assertEquals(2, customerReportVisitor.getCustomerList().size)
        assertTrue(customerReportVisitor.getCustomerList().contains(customer1))
        assertTrue(customerReportVisitor.getCustomerList().contains(customer2))
    }
}