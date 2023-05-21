import transactions.Transaction
import transactions.TransactionHistory

class Bank (
    val name: String, private val mediator: InterBankMediator,
    private var transactionHistory: TransactionHistory = TransactionHistory()
) {
    fun executeCommand(transaction: Transaction) {
        transaction.execute()
        transactionHistory.add(transaction)
    }

    fun getTransactionHistory(): TransactionHistory {
        return transactionHistory
    }
    fun sendMoneyToOtherBank(recipientBank: Bank, amount: Double) {
        mediator.transferMoney(this, recipientBank, amount)
    }
}