package transactions.concrete_transactions.account

import products.Account
import transactions.Transaction
import transactions.TransactionType

class SwitchToDebitAccount(private val account: Account, private val limit: Double) :
    Transaction(TransactionType.DEBIT) {
    override fun execute() {
        product = account
        account.switchToDebit(limit)
        description = "Debit is turned on. Balance: ${account.balance}"
        account.addToTransactionHistory(this)
    }
}