package products

import transactions.TransactionHistory
import java.time.LocalDate

abstract class Product(
    var id: String,
    var owner: String,
    var dateOpened: LocalDate,
    var balance: Double,
    var transactionHistory: TransactionHistory
)
