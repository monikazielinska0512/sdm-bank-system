package interestMechanisms

import BankMediator
import InterBankPaymentAgency
import Withdrawal
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import products.Product
import bank.Bank
import bank.Customer
import org.junit.jupiter.api.BeforeEach
import products.Account
import transactions.Transaction
import transactions.TransactionHistory
import java.time.LocalDate

class InterestAlgorithm3Test {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var mediator: BankMediator

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm3())
        account.balance = 1000.0
    }

    @Test
    fun calculateInterest() {
        bank.executeCommand(Withdrawal(account, 100.0))
        account.addToTransactionHistory(Withdrawal(account, 100.0))

        val expectedInterest = account.getTransactionHistory().size().toDouble() * 0.33 +
                account.balance * 0.09 +
                account.getDateOpened().year * 0.005

        val actualInterest = account.calculateInterest()
        assertEquals(expectedInterest, actualInterest, 0.01)
    }
}
