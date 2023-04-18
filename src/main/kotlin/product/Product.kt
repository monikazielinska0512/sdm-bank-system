package product

import Transaction
import java.util.*

abstract class Product(
    val id: String,
    val owner: String,
    val dateOpened: Date,
    var balance: Double,
    var history: MutableList<Transaction>
) {
    abstract fun add(amount: Double)
    abstract fun withdrawal(amount: Double)
    abstract fun transfer(amount: Double, recipient: Product)

    fun getBalance(): Double {
        return balance
    }

    fun getHistory(): List<Transaction> {
        return history
    }
}



