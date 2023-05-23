package products

import Customer
import InterestMechanism
import reporting.ReportVisitor
import java.time.LocalDate
import java.util.*

class Account(
    owner: Customer,
    dateOpened: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, interestMechanism) {

    val associatedProducts: Map<String, MutableList<Product>> = mapOf(
        "deposits" to mutableListOf(),
        "loans" to mutableListOf()
    )
    private var isDebit: Boolean = false
    fun switchToDebit() {
        this.isDebit = true;
    }

    fun getIsDebit(): Boolean {
        return this.isDebit
    }

    override fun transfer(receiver: Product, amount: Double) {

        if (isDebit) {
            balance -= amount
            receiver.balance += amount
        } else {
            if (amount > balance) {
                throw Exception("Not enough money")
            } else {
                balance -= amount
                receiver.balance += amount
            }
        }
    }

    override fun withdrawMoney(amount: Double){
        if (isDebit) {
            balance -= amount
        } else {
            if (amount > balance) {
                throw Exception("Not enough money")
            } else {
                balance -= amount
            }
        }
    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}