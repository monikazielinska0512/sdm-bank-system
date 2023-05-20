package products

import transactions.TransactionHistory
import java.time.Duration
import java.time.LocalDate
import java.util.*

class Account(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory,
    private var deposit: Deposit? = null
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, transactionHistory) {

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

    fun openDeposit(period: Duration) {
        val transactionHistory = TransactionHistory();
        this.deposit = Deposit(this, 0.0, period, this.getOwner(), LocalDate.now(), 0.0, transactionHistory)
    }

    fun getDeposit(): Deposit? {
        return this.deposit
    }

    fun transferToDeposit(amount: Double) {
        if (this.deposit == null) {
            throw Exception("No deposit to transfer to")
        } else {
            this.deposit?.addMoney(amount)
            this.removeMoney(this.balance)
        }
    }

    fun takeLoan(amount: Double) {
        val loan = Loan(this.getOwner(), this, amount, LocalDate.now(), 0.0, TransactionHistory())
        this.addMoney(amount)
    }
}