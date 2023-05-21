import java.time.LocalDate
import products.Account
import transactions.Transaction
import transactions.TransactionType

class TransferTransaction(
    private var senderAccount: Account,
    private var receiverAccount: Account,
    var amount: Double,
    executionDate: LocalDate,
    description: String? = null
) : Transaction(TransactionType.TRANSFER, executionDate, description) {
    override fun execute() {
        senderAccount.transfer(receiverAccount, amount)
    }
}
