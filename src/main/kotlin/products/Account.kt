package products

import transactions.Transaction
import transactions.TransactionHistory
import java.time.LocalDate
import java.util.*

class Account(
    owner: String, dateOpened: LocalDate, balance: Double, transactionHistory: TransactionHistory
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, transactionHistory) {
    fun open() {
        println("Account opened [ Name: ${this.getOwner()}, Balance: ${balance}, DateOpened: ${this.getDateOpened()}, ${this.getTransactionHistory().history}]")
    }

    fun close() {
        println("Account closed [ Name: ${this.getOwner()}, Balance: ${balance}, DateOpened: ${this.getDateOpened()}, ${this.getTransactionHistory().history}] closed")
    }

    fun addToTransactionHistory(transaction: Transaction) {
        this.getTransactionHistory().add(transaction)
    }

    fun addMoney(amount: Double) {
        println("Added [ Name: ${this.getOwner()}, Balance: ${balance}, DateOpened: ${this.getDateOpened()}, ${this.getTransactionHistory().history}]")
        balance += amount
    }

    fun removeMoney(amount: Double) {
        println("Removed [ Name: ${this.getOwner()}, Balance: ${balance}, DateOpened: ${this.getDateOpened()}, ${this.getTransactionHistory().history}]")
        balance -= amount
    }

}