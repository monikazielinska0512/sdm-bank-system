package product

import Transaction
import TransactionType
import jdk.nashorn.internal.objects.NativeMath.round
import java.time.LocalDateTime
import java.util.*

class Loan(
    id: String,
    owner: String,
    dateOpened: Date,
    balance: Double,
    history: MutableList<Transaction>,
    private val account: Account,
    private val amount: Double,
    private val interestRate: Double,
) : Product(id, owner, dateOpened, balance, history) {

    fun takeLoan(amount: Double): Boolean {
        if (amount <= account.balance) {
            account.add(amount)
            history.add(Transaction(TransactionType.TAKE_LOAN, LocalDateTime.now(), "Loan taken - $amount on ${Date()}"))
            return true
        } else {
            return false
        }
    }

    fun calculateInterest(): Double {
        val interest = round(balance * interestRate, 2)
        account.add(interest)
        history.add(
            Transaction(
                TransactionType.INTEREST_CALCULATION,
                LocalDateTime.now(),
                "Added interest on ${Date()}"
            )
        )
        return interest
    }


    override fun add(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun withdrawal(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun transfer(amount: Double, recipient: Product) {
        TODO("Not yet implemented")
    }
}
