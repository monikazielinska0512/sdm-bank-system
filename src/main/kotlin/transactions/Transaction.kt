package transactions

import products.Product
import java.time.LocalDate
import java.time.LocalDateTime

enum class TransactionType {
    CLOSE_ACCOUNT, OPEN_ACCOUNT, CLOSE_DEPOSIT,
    OPEN_DEPOSIT, TAKE_LOAN, WITHDRAWAL,
    TRANSFER, DEBIT, DEPOSIT_TRANSFER, INTEREST_CALCULATION, REPORTING, INTERBANK
}

abstract class Transaction(
    var type: TransactionType,
    var description: String? = null,
    var product: Product? = null
) {
    val id: String = java.util.UUID.randomUUID().toString()
    var executionDate: LocalDateTime = LocalDateTime.now()

    abstract fun execute()
}