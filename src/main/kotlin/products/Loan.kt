package products

import bank.Customer
import InterestMechanism
import reporting.ReportVisitor
import bank.Bank
import java.time.LocalDate
import java.time.Period
import java.util.*

class Loan(
    owner: Customer,
    private var associatedAccount: Account,
    private var value: Double,
    private var period: Period,
    dateOpened: LocalDate,
    interestMechanism: InterestMechanism,
    bank: Bank = associatedAccount.bank
) : Product(
    UUID.randomUUID().toString(), owner, dateOpened, value, interestMechanism, bank
) {
    private var repaymentValue: Double = value + calculateInterest()
    private var isPaidOff: Boolean = false

    fun getAssociatedAccount(): Account {
        return associatedAccount
    }

    fun getValue(): Double {
        return value
    }

    fun getPeriod(): Period {
        return period
    }

    fun getRepaymentValue(): Double {
        return repaymentValue
    }

    fun getIsPaidOff(): Boolean {
        return isPaidOff
    }

    fun open(account: Account, amount: Double) {
        this.associatedAccount = account
        this.associatedAccount.addMoney(amount)
        account.associatedProducts["loans"]?.add(this)
        this.addToBank()
    }

    override fun close() {
        this.associatedAccount.withdrawMoney(repaymentValue)
        repaymentValue = 0.0
        this.isPaidOff = true
        this.associatedAccount.associatedProducts["loans"]?.remove(this)
    }


    fun payRate(amount: Double) {
        // Payment does not cover the full repaymentValue
        if (repaymentValue - amount > 0.0) {
            this.associatedAccount.withdrawMoney(amount)
            repaymentValue -= amount
        } else {
            close()
        }
    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}