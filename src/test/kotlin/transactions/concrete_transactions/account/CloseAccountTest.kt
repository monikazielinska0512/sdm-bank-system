package transactions.concrete_transactions.account

import InterbankPaymentAgency
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import products.Account
import transactions.TransactionType
import java.time.LocalDate

class CloseAccountTest {

    private lateinit var account: Account
    private lateinit var closeAccountTransaction: CloseAccount
    private lateinit var bank: Bank
    private lateinit var customer: Customer

    @BeforeEach
    fun setUp() {
        bank =  Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        account = Account(customer, LocalDate.now(), 0.0, InterestAlgorithm2(), bank)
        closeAccountTransaction = CloseAccount(account)
    }

    @Test
    fun testExecute() {
        // Execute the transaction
        closeAccountTransaction.execute()

        // Assert the transaction type
        assertEquals(TransactionType.CLOSE_ACCOUNT, closeAccountTransaction.type)

        // Assert the account state after execution
        assertEquals(false, account.getIsActive())
        assertEquals(0.0, account.balance, 0.001)

        // Assert the transaction description
        val expectedDescription = "Account was closed. Balance: 0.0"
        assertEquals(expectedDescription, closeAccountTransaction.description)

        // Assert the transaction is added to the account's transaction history
        assertEquals(1, account.getTransactionHistory().size())
        assertEquals(closeAccountTransaction, account.getTransactionHistory().getHistory()[0])
    }
}