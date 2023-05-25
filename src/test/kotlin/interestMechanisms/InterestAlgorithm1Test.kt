package interestMechanisms

import InterbankPaymentAgency
import bank.Bank
import bank.Customer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import products.Product

class InterestAlgorithm1Test {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account

    @BeforeEach
    fun setUp() {
        bank = Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm1())
        account.balance = 1000.0
    }
    @Test
    fun calculateInterest() {
        val interestMechanism = InterestAlgorithm1()
        val expectedInterest = 1000.0 * 0.05
        val actualInterest = interestMechanism.calculateInterest(account)
        assertEquals(expectedInterest, actualInterest)
    }
}
