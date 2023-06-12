package products

import bank.Customer
import InterestMechanism
import reporting.ReportVisitor
import bank.Bank
import java.time.LocalDate
import java.time.Period
import java.util.*

class Deposit(
    private val associatedAccount: Account,
    var calculatedInterest: Double,
    var period: Period,
    owner: Customer,
    dateOpened: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism,
    bank: Bank = associatedAccount.bank

) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, interestMechanism, bank) {

    private val closingDate: LocalDate = dateOpened + period
    fun getAssociatedAccount(): Account {
        return associatedAccount
    }


    override fun transfer(receiver: Product, amount: Double) {
        if (LocalDate.now() < closingDate) {
            println("Earlier withdrawal of money. You will lose your interest.")
            withdrawAndClose()
        } else {
            super.transfer(receiver, amount)
        }
    }

    override fun close() {
        if (associatedAccount.associatedProducts["deposits"]?.contains(this) == true) {
            withdrawAndClose()
            associatedAccount.associatedProducts["deposits"]?.remove(this)
        } else {
            throw IllegalArgumentException("Deposit is not associated with this account.")
        }
    }

    override fun open() {
        associatedAccount.associatedProducts["deposits"]?.add(this)
        this.addToBank()
    }

    private fun withdrawAndClose() {
        calculatedInterest = calculateInterest()
        associatedAccount.addMoney(calculatedInterest + balance)
        calculatedInterest = 0.0
        balance = 0.0

    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}
