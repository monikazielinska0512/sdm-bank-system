package transactions.concrete_transactions.product

import InterBankPaymentAgency
import Withdrawal
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import java.time.LocalDate
import javax.swing.TransferHandler.TransferSupport

class TransferTest {

    private lateinit var bank: Bank
    private lateinit var mediator: InterBankPaymentAgency
    private lateinit var customer1: Customer
    private lateinit var customer2: Customer
    private lateinit var account1: Account
    private lateinit var account2: Account

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
        customer1 = Customer("John", "Doe", bank)
        customer2 = Customer("Doe", "John", bank)
        account1 = Account(customer1, LocalDate.now(), 500.0, InterestAlgorithm2(), bank)
        account2 = Account(customer2, LocalDate.now(), 500.0, InterestAlgorithm2(), bank)
    }

    @Test
    fun execute() {
        val initialBalance1 = account1.balance
        val initialBalance2 = account2.balance
        val amount = 50.0
        val transfer = Transfer(account1, account2, amount)
        transfer.execute()
        kotlin.test.assertEquals(initialBalance1 - amount, account1.balance, 0.01)
        kotlin.test.assertEquals(initialBalance2 + amount, account2.balance, 0.01)
    }
}