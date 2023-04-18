import java.time.LocalDateTime

enum class TransactionType {
    TRANSFER,
    PAYMENT,
    WITHDRAWAL,
    INTEREST_CALCULATION,
    DEPOSIT_CLOSURE,
    TAKE_LOAN
}

data class Transaction(val type: TransactionType, val date: LocalDateTime, val description: String) {
    override fun toString(): String {
        return "$date - $type: $description"
    }
}
