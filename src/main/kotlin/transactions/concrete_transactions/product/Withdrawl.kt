import products.Product
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Withdrawal(private var transactionProduct: Product, private var amount: Double) :
    Transaction(TransactionType.WITHDRAWAL) {
    override fun execute() {
        product = transactionProduct
        transactionProduct.withdrawMoney(amount)
        description = "Amount: $amount was withdrawn. Balance: ${transactionProduct.balance}"
    }
}