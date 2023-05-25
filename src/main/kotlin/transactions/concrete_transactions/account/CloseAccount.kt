package transactions.concrete_transactions.account

import products.Account
import transactions.Transaction
import transactions.TransactionType

class CloseAccount(var account: Account) : Transaction(
    TransactionType.CLOSE_ACCOUNT) {
    override fun execute() {
        product = account
        account.close()
        updateDescription()
        updateTransactionHistory()
    }

    private fun updateDescription(){
        description = "Account was closed. Balance: ${account.balance}"
    }

    private fun updateTransactionHistory(){
        account.addToTransactionHistory(this)
    }
}