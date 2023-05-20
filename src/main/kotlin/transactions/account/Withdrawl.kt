package transactions.account

import products.Account
import products.Product
import transactions.Transaction

class Withdrawl(var account: Account, var amount: Double) : Transaction() {
    override fun execute() {
        return account.removeMoney(amount)
    }
}