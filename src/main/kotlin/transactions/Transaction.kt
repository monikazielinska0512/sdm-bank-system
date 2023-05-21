package transactions

import java.time.LocalDate

enum class TransactionType {
    CLOSE_ACCOUNT, OPEN_ACCOUNT, CLOSE_DEPOSIT,
    OPEN_DEPOSIT, TAKE_LOAN, TRANSFER_TO_DEPOSIT, WITHDRAWAL,
    TRANSFER, INTEREST_CALCULATION, CREDIT_PAYMENT, DEBIT, REPORTING
}

abstract class Transaction(
    var type: TransactionType,
    var executionDate: LocalDate,
    var description: String? = null,
) {
    abstract fun execute()
}