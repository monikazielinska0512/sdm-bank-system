import products.Deposit
import transactions.Transaction
import transactions.TransactionType

class DepositTransfer(private val deposit: Deposit) :
    Transaction(
        TransactionType.DEPOSIT_TRANSFER
    ) {
    override fun execute() {
        product = deposit
        deposit.transfer(deposit.getAssociatedAccount(), deposit.balance + deposit.calculatedInterest)
        description = "Transfer from deposit."
        deposit.addToTransactionHistory(this)
        deposit.getAssociatedAccount().addToTransactionHistory(this)
    }
}