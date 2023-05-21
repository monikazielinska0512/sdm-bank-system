package transactions

import java.time.LocalDate

enum class TransactionType {
    CLOSE_ACCOUNT, OPEN_ACCOUNT, CLOSE_DEPOSIT,
    OPEN_DEPOSIT, TAKE_LOAN, WITHDRAWAL,
    TRANSFER, DEBIT, DEPOSIT_TRANSFER, INTEREST_CALCULATION, REPORTING
}

abstract class Transaction(
    var type: TransactionType,
    var executionDate: LocalDate,
    var description: String? = null,
) {
    abstract fun execute()
}