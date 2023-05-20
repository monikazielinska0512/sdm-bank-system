package transactions.concrete_transactions
import products.Account
import transactions.Transaction

class TransferToDeposit(private var account: Account, private var amount: Double) : Transaction() {
    override fun execute() {
        account.transferToDeposit(amount)
        account.getTransactionHistory().add(this)
        account.getDeposit()?.getTransactionHistory()?.add(this)
        println(" ${account.getOwner()} transferred ${amount} to their deposit.")
    }
}