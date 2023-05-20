package products

import transactions.TransactionHistory
import java.time.LocalDate
import java.util.*

class Account(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, transactionHistory) {

    fun addMoney(amount: Double) {
        balance += amount
    }

    fun removeMoney(amount: Double) {
        balance -= amount
    }
}