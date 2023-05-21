package products

import InterestMechanism
import transactions.Transaction
import transactions.TransactionHistory
import java.time.LocalDate

class Account(
    id: String,
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory, interestMechanism: InterestMechanism
) : Product(id, owner, dateOpened, balance, transactionHistory, interestMechanism) {
    fun open() {
        println("Account opened [ Name: $owner, Balance: $balance, DateOpened: $dateOpened, ${transactionHistory.history}]")
    }

    fun close() {
        println("Account closed [ Name: $owner, Balance: $balance, DateOpened: $dateOpened, ${transactionHistory.history}] closed")
    }

    fun addToTransactionHistory(transaction: Transaction) {
        transactionHistory.add(transaction)
    }
}