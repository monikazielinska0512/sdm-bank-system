package products

import transactions.TransactionHistory
import java.time.LocalDate

// Receiver
abstract class Product(
    private var id: String,
    private var owner: String,
    private var dateOpened: LocalDate,
    var balance: Double,
    private var transactionHistory: TransactionHistory
) {

    fun getOwner(): String {
        return owner
    }

    fun getDateOpened(): LocalDate {
        return dateOpened
    }

    fun getTransactionHistory(): TransactionHistory {
        return transactionHistory
    }
}
