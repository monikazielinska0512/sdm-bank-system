package transactions

import InterBankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import transactions.concrete_transactions.account.CloseAccount
import transactions.concrete_transactions.account.OpenAccount
import java.time.LocalDate

class TransactionHistoryTest {
    private lateinit var transactionHistory: TransactionHistory
    private lateinit var account: Account
    private lateinit var openAccountTransaction: OpenAccount
    private lateinit var closeAccountTransaction: CloseAccount
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var mediator: InterBankPaymentAgency

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        transactionHistory = TransactionHistory()
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = Account(customer, LocalDate.now(), 0.0, InterestAlgorithm2(), bank)
        openAccountTransaction = OpenAccount(account)
        closeAccountTransaction = CloseAccount(account)
    }

    @Test
    fun testAddTransaction() {
        transactionHistory.add(openAccountTransaction)

        assertEquals(1, transactionHistory.size())
        assertEquals(openAccountTransaction, transactionHistory.getHistory()[0])
    }

    @Test
    fun testRemoveTransaction() {
        transactionHistory.add(openAccountTransaction)
        transactionHistory.add(closeAccountTransaction)

        val removedTransaction = transactionHistory.remove()

        assertEquals(closeAccountTransaction, removedTransaction)
        assertEquals(1, transactionHistory.size())
    }

    @Test
    fun testGetHistory() {
        transactionHistory.add(openAccountTransaction)
        transactionHistory.add(closeAccountTransaction)

        val history = transactionHistory.getHistory()

        assertEquals(2, history.size)
        assertEquals(openAccountTransaction, history[0])
        assertEquals(closeAccountTransaction, history[1])
    }
}
