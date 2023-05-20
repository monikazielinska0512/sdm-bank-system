package transactions.concrete_transactions

import products.Account
import transactions.Transaction

class TakeLoan(private var account: Account, private var amount: Double) : Transaction() {
    override fun execute() {
        account.takeLoan(amount)
        account.getTransactionHistory().add(this)
        println("Take Loan: ${account.getOwner()} took a loan.")
    }
}