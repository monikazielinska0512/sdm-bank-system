package product

import Transaction
import TransactionType
import java.time.LocalDateTime
import java.util.*

class Account(
    id: String,
    owner: String,
    dateOpened: Date,
    balance: Double,
    history: MutableList<Transaction>,
    var interestRate: Double
) : Product(id, owner, dateOpened, balance, history) {

    override fun add(amount: Double) {
        balance += amount
        history.add(Transaction(TransactionType.PAYMENT, LocalDateTime.now(), "Added $amount on ${Date()}"))
    }

    override fun withdrawal(amount: Double) {
        if (amount > balance) {
            throw IllegalArgumentException("Not enough funds")
        }
        balance -= amount
        history.add(Transaction(TransactionType.WITHDRAWAL, LocalDateTime.now(), "Withdrew $amount on ${Date()}"))
    }

    override fun transfer(amount: Double, recipient: Product) {
        if (amount > balance) {
            throw IllegalArgumentException("Not enough funds")
        }
        balance -= amount
        recipient.add(amount)
        history.add(
            Transaction(
                TransactionType.TRANSFER,
                LocalDateTime.now(),
                "Transferred $amount to ${recipient.id} on ${Date()}"
            )
        )
    }

    fun addInterest() {
        balance += balance * interestRate
        history.add(
            Transaction(
                TransactionType.INTEREST_CALCULATION,
                LocalDateTime.now(),
                "Added interest on ${Date()}"
            )
        );
    }
}