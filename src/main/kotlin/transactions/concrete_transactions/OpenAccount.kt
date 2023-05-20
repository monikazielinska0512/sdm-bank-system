package transactions.concrete_transactions
import products.Account
import transactions.Transaction
class OpenAccount(private var account: Account) : Transaction() {
    override fun execute() {
        account.open()
        account.getTransactionHistory().add(this)
        println("Open Account: ${account.getOwner()} opened their account.")
    }
}