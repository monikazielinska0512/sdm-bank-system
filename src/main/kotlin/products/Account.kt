package products


import InterestMechanism
import transactions.Transaction
import transactions.TransactionHistory
import java.time.Duration
import java.time.LocalDate
import java.util.*

class Account(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    val associatedProducts: MutableList<Product> = mutableListOf()
    transactionHistory: TransactionHistory, interestMechanism: InterestMechanism
) : Product(id, owner, dateOpened, balance, transactionHistory, interestMechanism) {
    fun open() {
        println("Account opened [ Name: $owner, Balance: $balance, DateOpened: $dateOpened, ${transactionHistory.history}]")
    }

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