package transactions.concrete_transactions

import products.Account
import transactions.Transaction
import java.time.Duration
import java.time.Period

class OpenDeposit(private var account: Account, private var period: Duration) : Transaction() {
    override fun execute() {
        account.openDeposit(period)
        account.getTransactionHistory().add(this)
        account.getDeposit()?.getTransactionHistory()?.add(this)
        println("Open Deposit: ${account.getOwner()} opened their deposit.")
    }
}