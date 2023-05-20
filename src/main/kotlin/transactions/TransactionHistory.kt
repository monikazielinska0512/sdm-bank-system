package transactions
import java.util.*

class TransactionHistory {
    val history = Stack<Transaction>()
    fun add(c: Transaction) {
        history.push(c)
    }
    fun remove(): Transaction {
        return history.pop()
    }
}