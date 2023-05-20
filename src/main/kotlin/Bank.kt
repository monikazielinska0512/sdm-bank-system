import transactions.Transaction

class Bank {
    fun executeCommand(transaction: Transaction) {
        transaction.execute()
    }
}
