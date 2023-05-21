import transactions.Transaction
import transactions.TransactionHistory

class Bank(
    private var transactionHistory: TransactionHistory = TransactionHistory()
) {
    fun executeCommand(transaction: Transaction) {
        transaction.execute()
        transactionHistory.add(transaction)
    }
}
