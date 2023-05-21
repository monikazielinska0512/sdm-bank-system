package products

import InterestMechanism
import transactions.TransactionHistory
import java.time.LocalDate

abstract class Product(
    var id: String,
    var owner: String,
    var dateOpened: LocalDate,
    var balance: Double,
    var transactionHistory: TransactionHistory,
    private var interestMechanism: InterestMechanism
) {
    fun calculateInterest() {
        interestMechanism.calculateInterest()
    }
}
