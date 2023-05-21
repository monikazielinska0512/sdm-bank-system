package transactions.concrete_transactions
import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class OpenAccount(owner: String) : Transaction(TransactionType.OPEN_ACCOUNT, LocalDate.now()) {
    private var owner: String = owner
    override fun execute() {
        val account = Account(owner, LocalDate.now(), 0.0)
        account.addToTransactionHistory(this)
        println("Open Account: ${account.getOwner()} opened their account.")
    }
}