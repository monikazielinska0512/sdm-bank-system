package products

import InterestMechanism
import java.time.LocalDate
import java.util.*

class Account(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, interestMechanism) {

    val associatedProducts: Map<String, MutableList<Product>> = mapOf(
        "deposits" to mutableListOf(),
        "loans" to mutableListOf()
    )
    private var isDebit: Boolean = false
    private var debitLimit = 0.0;
    fun switchToDebit(limit: Double) {
        this.isDebit = true;
        this.debitLimit = limit;
    }

    fun getIsDebit(): Boolean {
        return this.isDebit
    }

    override fun transfer(receiver: Product, amount: Double) {

        if (isDebit) {
            if (balance - amount < debitLimit) {
                throw Exception("Debit limit exceeded")
            } else {
                balance -= amount
                receiver.balance += amount
            }
        } else {
            if (amount > balance) {
                throw Exception("Not enough money")
            } else {
                balance -= amount
                receiver.balance += amount
            }
        }
    }

    override fun withdrawMoney(amount: Double) {
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
}