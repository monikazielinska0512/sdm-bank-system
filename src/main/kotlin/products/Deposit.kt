package products

import transactions.TransactionHistory
import java.time.Duration
import java.time.LocalDate
import java.util.*

class Deposit(
    private var associatedAccount: Account,
    private var calculatedInterest: Double,
    private var period: Duration,
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
) : Product(
    UUID.randomUUID().toString(), owner, dateOpened, balance
) {
    fun addMoney(amount: Double) {
        balance += amount
    }

    fun getAssociatedAccount(): Account {
        return associatedAccount
    }

    fun withdrawMoneyToAccount() {
        if (LocalDate.now() < this.getDateOpened() + period ) {
            balance = 0.0
            associatedAccount.addMoney(balance)
            calculatedInterest = 0.0
        }
        else{
            associatedAccount.addMoney(balance + calculatedInterest)
            balance = 0.0
        }
    }
    fun close() {
        associatedAccount.addMoney(balance + calculatedInterest)
        balance = 0.0
    }
}