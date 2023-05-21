import transactions.Transaction
import transactions.TransactionHistory

class Bank(
    private var transactionHistory: TransactionHistory = TransactionHistory(),
    var entities: MutableList<BankEntity> = mutableListOf()
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
