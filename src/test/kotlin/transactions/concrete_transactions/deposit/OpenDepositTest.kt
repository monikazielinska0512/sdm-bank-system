package transactions.concrete_transactions.deposit

import InterBankPaymentAgency
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
import java.time.Period

class OpenDepositTest {
    private lateinit var bank: Bank
    private lateinit var customer: Customer
    private lateinit var account: Account
    private lateinit var deposit: Deposit
    private lateinit var openDepositTransaction: OpenDeposit
    private lateinit var mediator: InterBankPaymentAgency
    @BeforeEach
    fun setUp() {
        mediator = InterBankPaymentAgency()
        bank = Bank("MyBank", mediator)
        customer = Customer("John", "Doe", bank)
        account = bank.createAccount(customer, InterestAlgorithm3())
        account.balance = 1000.0
        openDepositTransaction = OpenDeposit(account, Period.ofMonths(12), InterestAlgorithm1())
    }

    @Test
    fun testExecute() {
        openDepositTransaction.execute()
        deposit = account.associatedProducts["deposits"]?.get(0) as Deposit
        assertEquals(TransactionType.OPEN_DEPOSIT, openDepositTransaction.type)
        assertEquals(1, deposit.getTransactionHistory().getHistory().size)
        assertEquals(openDepositTransaction, deposit.getTransactionHistory().getHistory()[0])
        assertEquals(2, deposit.getAssociatedAccount().getTransactionHistory().getHistory().size)
        assertEquals(openDepositTransaction, deposit.getAssociatedAccount().getTransactionHistory().getHistory()[1])
    }
}