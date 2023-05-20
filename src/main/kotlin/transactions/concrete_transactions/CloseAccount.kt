package transactions.concrete_transactions

import products.Account
import transactions.Transaction

class CloseAccount(var account: Account) : Transaction() {
    override fun execute() {
//        account.close()
        account.getTransactionHistory().add(this)
        println("Close Account: ${account.getOwner()} closed their account.")
    }
}