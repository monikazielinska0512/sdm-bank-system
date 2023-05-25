import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm1
import interestMechanisms.InterestAlgorithm3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import products.Account
import products.Deposit
import transactions.TransactionType
import transactions.concrete_transactions.deposit.CloseDeposit
import java.time.LocalDate
import java.time.Period

class CloseDepositTest {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var deposit: Deposit
    private lateinit var closeDepositTransaction: CloseDeposit


    @BeforeEach
    fun setUp() {
        bank = Bank("MyBank", InterbankPaymentAgency())
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm3())
        account.balance = 1000.0
        deposit = Deposit(account, 0.0, Period.ofMonths(12), customer, LocalDate.now(), 100.0, InterestAlgorithm1(), account.bank)
        account.associatedProducts["deposits"]?.add(deposit)
        closeDepositTransaction = CloseDeposit(deposit)
    }

    @Test
    fun testExecute() {
        // Execute the transaction
        closeDepositTransaction.execute()

        // Assert the transaction type
        assertEquals(TransactionType.CLOSE_DEPOSIT, closeDepositTransaction.type)

        // Assert the transaction description
        val expectedDescription = "Deposit was closed. Calculated interest: ${deposit.calculatedInterest}"
        assertEquals(expectedDescription, closeDepositTransaction.description)

        // Assert the transaction is added to the deposit's transaction history
        assertEquals(1, deposit.getTransactionHistory().getHistory().size)
        assertEquals(closeDepositTransaction, deposit.getTransactionHistory().getHistory()[0])

        // Assert the transaction is added to the associated account's transaction history
        assertEquals(2, deposit.getAssociatedAccount().getTransactionHistory().getHistory().size)
        assertEquals(closeDepositTransaction, deposit.getAssociatedAccount().getTransactionHistory().getHistory()[1])
    }
}