package transactions.concrete_transactions

import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class CloseAccount(var account: Account) : Transaction(TransactionType.CLOSE_ACCOUNT,
    LocalDate.now()
) {
    override fun execute() {
        account.addToTransactionHistory(this)
        println("Close Account: ${account.getOwner()} closed their account.")
    }
}