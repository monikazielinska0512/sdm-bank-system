package products

import bank.Customer
import InterestMechanism
import reporting.ReportVisitor
import bank.Bank
import java.time.LocalDate
import java.util.*

class Account(
    accountOwner: Customer,
    openingDate: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism,
    bank: Bank

) : Product(
    UUID.randomUUID().toString(),
    accountOwner,
    openingDate,
    balance,
    interestMechanism,
    bank
) {


    // getters setters
    fun getDebitLimit(): Double {
        return debitLimit
    }

    fun getIsActive(): Boolean {
        return isActive
    }

    fun getClosedDate(): LocalDate? {
        return closedDate
    }


    val associatedProducts: Map<String, MutableList<Product>> = mapOf(
        "deposits" to mutableListOf(),
        "loans" to mutableListOf()
    )

    private var isDebit: Boolean = false
    private var isActive: Boolean = true
    private var debitLimit: Double = 0.0
    private var closedDate: LocalDate? = null

    fun switchToDebit(limit: Double) {
        isDebit = true
        debitLimit = limit
    }

    fun getIsDebit(): Boolean {
        return isDebit
    }

    override fun transfer(receiver: Product, amount: Double) {
        if (isDebit) {
            if (balance - amount < debitLimit) {
                throw IllegalArgumentException("Transfer failed. Debit limit exceeded")
            } else {
                balance -= amount
                receiver.balance += amount
            }
        } else {
            if (amount > balance) {
                throw IllegalArgumentException("Transfer failed. Insufficient funds")
            } else {
                balance -= amount
                receiver.balance += amount
            }
        }
    }

    override fun withdrawMoney(amount: Double) {
        if (isDebit) {
            if (balance - amount < debitLimit) {
                throw IllegalArgumentException("Withdrawal failed. Debit limit exceeded")
            } else {
                balance -= amount
            }
        } else {
            if (amount > balance) {
                throw IllegalArgumentException("Withdrawal failed. Insufficient funds")
            } else {
                balance -= amount
            }
        }
    }

    fun close() {
        isActive = false
        closedDate = LocalDate.now()
    }

    fun open() {
        this.addToBank()
    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}
