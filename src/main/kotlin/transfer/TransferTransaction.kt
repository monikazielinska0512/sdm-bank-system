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
        println("BEFORE: Sender: ${senderAccount.getOwner()}, Receiver: ${receiverAccount.getOwner()}, Amount: ${amount}, Sender Balance: ${senderAccount.balance} Receiver Balance: ${receiverAccount.balance}")
        senderAccount.withdrawMoney(amount)
        receiverAccount.addMoney(amount)
        description =
            "Sender: ${senderAccount.getOwner()}, Receiver: ${receiverAccount.getOwner()}, Amount: ${amount}, Sender Balance: ${senderAccount.balance} Receiver Balance: ${receiverAccount.balance}"
    }
}
