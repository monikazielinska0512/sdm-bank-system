package products


import InterestMechanism
import transactions.Transaction
import transactions.TransactionHistory
import java.time.LocalDate

abstract class Product(
    private var id: String,
    private var owner: String,
    private var dateOpened: LocalDate,
    var balance: Double,
    private var interestMechanism: InterestMechanism,
    private var transactionHistory: TransactionHistory = TransactionHistory()
) {

    fun getOwner(): String {
        return owner
    }

    fun getDateOpened(): LocalDate {
        return dateOpened
    }

    fun addToTransactionHistory(transaction: Transaction) {
        transactionHistory.add(transaction)
    }

    fun calculateInterest() {
        interestMechanism.calculateInterest(this)
    }
}
