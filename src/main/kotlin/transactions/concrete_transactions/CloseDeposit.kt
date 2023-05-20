package transactions.concrete_transactions
import products.Deposit
import transactions.Transaction
class CloseDeposit(private var deposit: Deposit) : Transaction() {
    override fun execute() {
        deposit.close()
        deposit.getTransactionHistory().add(this)
        deposit.getAssociatedAccount().getTransactionHistory().add(this)
    }
}