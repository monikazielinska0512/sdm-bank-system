package transactions.account
import products.Account
import products.Product
import transactions.Transaction
class OpenAccount(private var account: Account) : Transaction() {
    override fun execute() {
        account.open()
    }
    override fun transaction(product: Product) {
        this.product = account
    }
}