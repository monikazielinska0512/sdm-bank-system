import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm2
import products.Account
import transactions.concrete_transactions.product.Transfer
import java.time.LocalDate

class InterBankPaymentAgencyTest {
    private lateinit var bank1: Bank
    private lateinit var bank2: Bank
    private lateinit var mediator: InterBankPaymentAgency
    private lateinit var customer1: Customer
    private lateinit var customer2: Customer
    private lateinit var account1: Account
    private lateinit var account2: Account

    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank1 = Bank("MyBank", mediator)
        bank2 = Bank("MyBank", mediator)
        customer1 = Customer("John", "Doe", bank1)
        customer2 = Customer("Doe", "John", bank2)
        account1 = Account(customer1, LocalDate.now(), 1500.0, InterestAlgorithm2(), bank1)
        account2 = Account(customer2, LocalDate.now(), 1500.0, InterestAlgorithm2(), bank2)
        mediator.addBank(bank1)
        mediator.addBank(bank2)

    }

    @Test
    fun addTransfer() {
        val transfer = Transfer(account2, account1, 50.0)
        mediator.addTransfer(transfer)
        assertEquals(1, mediator.getTransfers().size)
        assertEquals(transfer, mediator.getTransfers()[0])
    }

    @Test
    fun getTransfer() {
        val transfer1 = Transfer(account1, account2, 100.0)
        val transfer2 = Transfer(account1, account2, 100.0)
        val transfer3 = Transfer(account2, account1, 100.0)
        val transfer4 = Transfer(account2, account1, 100.0)
        val transfer5 = Transfer(account1, account2, 100.0)

        mediator.addTransfer(transfer1)
        mediator.addTransfer(transfer2)
        mediator.addTransfer(transfer3)
        mediator.addTransfer(transfer4)
        mediator.addTransfer(transfer5)
        assertEquals(0, mediator.getTransfers().size)
    }

    @Test
    fun addBank() {
        assertEquals(2, mediator.banks.size)
        assertEquals(bank1, mediator.banks[0])
    }

    @Test
    fun sendPackage() {

        val transfer1 = Transfer(account1, account2, 100.0)
        val transfer2 = Transfer(account1, account2, 100.0)
        val transfer3 = Transfer(account2, account1, 100.0)

        mediator.addTransfer(transfer1)
        mediator.addTransfer(transfer2)
        mediator.addTransfer(transfer3)

        mediator.sendPackage()

        assertEquals(1400.0, account1.balance)
        assertEquals(1600.0, account2.balance)

    }
}
