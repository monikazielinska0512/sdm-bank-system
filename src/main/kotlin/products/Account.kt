package products

import transactions.TransactionHistory
import java.time.Duration
import java.time.LocalDate
import java.util.*

class Account(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    val associatedProducts: MutableList<Product> = mutableListOf()
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance) {

    fun addMoney(amount: Double) {
        balance += amount
    }

    fun removeMoney(amount: Double) {
        if (amount > balance) {
            throw Exception("Not enough money in account")
        } else {
            balance -= amount
        }
    }
}