package product

import Transaction
import java.time.LocalDateTime
import java.util.*

class Deposit(
    id: String,
    owner: String,
    dateOpened: Date,
    balance: Double,
    history: MutableList<Transaction>,
    private var interestRate: Double,
    private val account: Account,
    private val durationInDays: Int
) : Product(id, owner, dateOpened, balance, history) {

    private var isClosed: Boolean = false
        private set

    override fun add(amount: Double) {
        if (isClosed) {
            throw IllegalStateException("Deposit is closed")
        }
        account.add(amount)
        history.add(Transaction(TransactionType.TRANSFER, LocalDateTime.now(), "Added $amount on ${Date()}"))
    }

    override fun withdrawal(amount: Double) {
        if (isClosed) {
            throw IllegalStateException("Deposit is closed")
        }
        throw UnsupportedOperationException("Cannot deduct funds from a deposit")
    }


    override fun transfer(amount: Double, recipient: Product) {
        if (isClosed) {
            throw IllegalStateException("Deposit is closed")
        }
        throw UnsupportedOperationException("Cannot transfer funds from a deposit")
    }

    fun close() {
        if (isClosed) {
            throw IllegalStateException("Deposit is already closed")
        }
        isClosed = true
        val interest = balance * interestRate * durationInDays / 365
        balance += interest
        account.add(balance)
        history.add(
            Transaction(
                TransactionType.DEPOSIT_CLOSURE,
                LocalDateTime.now(),
                "Closed deposit on ${Date()}"
            )
        )
    }
}