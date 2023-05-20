package transactions
import products.Product
abstract class Transaction {
    protected lateinit var product: Product
    open fun transaction(product: Product) {
        this.product = product
    }
    abstract fun execute()
}