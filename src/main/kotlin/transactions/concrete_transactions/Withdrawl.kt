package transactions.concrete_transactions

import products.Account
import transactions.Transaction

class Withdrawl(var account: Account, var amount: Double) : Transaction() {
    override fun execute() {
        return account.removeMoney(amount)
    }
}