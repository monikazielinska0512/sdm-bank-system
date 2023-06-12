package transactions.concrete_transactions.account

import products.Account
import transactions.Transaction
import transactions.TransactionType

class OpenAccount(val account: Account) : Transaction(
    TransactionType.OPEN_ACCOUNT
) {
    override fun execute() {
        product = account
        account.open()
        description = "Account was opened. Balance: ${account.balance}"
        account.addToTransactionHistory(this)
    }
}