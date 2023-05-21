package products

import Customer
import InterestMechanism
import reporting.ReportVisitor
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.util.*

class Deposit(
    private var associatedAccount: Account,
    var calculatedInterest: Double,
    private var period: Period,
    owner: Customer,
    dateOpened: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism
) : Product(
    UUID.randomUUID().toString(), owner, dateOpened, balance, interestMechanism
) {
    private val closingDate: LocalDate = dateOpened + period

    fun getAssociatedAccount(): Account {
        return associatedAccount
    }

    override fun transfer(receiver: Product, amount: Double) {
        if (LocalDate.now() < closingDate) {
            println("Earlier withdraw of money. You will loose your interest")
            associatedAccount.addMoney(balance)
            balance = 0.0
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
        associatedAccount.associatedProducts["deposits"]?.add(this)
    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}
