package products

import bank.Customer
import InterestMechanism
import reporting.ReportVisitor
import bank.Bank
import java.time.LocalDate
import java.util.*

class DebitAccountDecorator(
    private val decoratedAccount: Account,
    private val debitLimit: Double
) : Account(
    decoratedAccount.getOwner(),
    decoratedAccount.getDateOpened(),
    decoratedAccount.balance,
    decoratedAccount.getInterestMechanism(),
    decoratedAccount.bank
) {
    override fun transfer(receiver: Product, amount: Double) {
        val availableFunds = balance + debitLimit
        if (amount > availableFunds) {
            throw IllegalArgumentException("Transfer failed. Insufficient funds")
        } else {
            decoratedAccount.transfer(receiver, amount)
        }
    }


    override fun withdrawMoney(amount: Double) {
        if (balance - amount < debitLimit) {
            throw IllegalArgumentException("Withdrawal failed. Debit limit exceeded")
        } else {
            decoratedAccount.withdrawMoney(amount)
        }
    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}
