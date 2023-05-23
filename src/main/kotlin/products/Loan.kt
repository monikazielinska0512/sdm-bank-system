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
    UUID.randomUUID().toString(), owner, dateOpened, 0.0, interestMechanism
) {
    private var repaymentValue: Double = calculateInterest()
    private var isPaidOff: Boolean = false

    fun open(account: Account, amount: Double) {
        this.associatedAccount = account
        this.associatedAccount.addMoney(amount)
        account.associatedProducts["loans"]?.add(this)
        this.addToBank()
    }

    private fun close() {
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