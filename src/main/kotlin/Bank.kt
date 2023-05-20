import transactions.Transaction
import transactions.TransactionHistory


// Command Invoker

class Bank {
    val transactionHistory: TransactionHistory = TransactionHistory()
    fun takeCommand(transaction: Transaction) {
        transactionHistory.add(transaction)
    }
    fun executeCommands() {
        for (transaction in transactionHistory.history) {
            transaction.execute()
        }
    }
}
