package products

import InterestMechanism
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.util.*

class Deposit(
    private var associatedAccount: Account,
    private var calculatedInterest: Double,
    private var period: Period,
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism
) : Product(
    UUID.randomUUID().toString(), owner, dateOpened, balance, interestMechanism
) {
    fun getAssociatedAccount(): Account {
        return associatedAccount
    }

    fun withdrawMoneyToAccount() {
        if (LocalDate.now() < this.getDateOpened() + period) {
            balance = 0.0
            associatedAccount.addMoney(balance)
            calculatedInterest = 0.0
        } else {
            associatedAccount.addMoney(balance + calculatedInterest)
            balance = 0.0
        }
    }

    fun close() {
        //TODO calculate interest
        associatedAccount.addMoney(balance + calculatedInterest)
        balance = 0.0
    }

    fun open() {
        associatedAccount.associatedProducts.get("deposits")?.add(this)
    }
}
