package transactions.account
import products.Account
import products.Product
import transactions.Transaction
class CloseAccount(var account: Account) : Transaction() {
    override fun execute() {
        account.close()
    }
    override fun transaction(product: Product) {
        this.product = account
    }
}