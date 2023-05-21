package products

import InterestMechanism
import java.time.LocalDate
import java.util.*

class Account(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    val associatedProducts: MutableList<Product> = mutableListOf(),
    interestMechanism: InterestMechanism
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, interestMechanism) {

    fun addMoney(amount: Double) {
        balance += amount
    }

    fun removeMoney(amount: Double) {
        if (amount > balance) {
            throw Exception("Not enough money in account")
        } else {
            balance -= amount
        }
    }
}