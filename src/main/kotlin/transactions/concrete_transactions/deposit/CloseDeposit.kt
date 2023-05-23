package transactions.concrete_transactions.deposit

import products.Deposit
import transactions.Transaction
import transactions.TransactionType

class CloseDeposit(private val closedDeposit: Deposit) : Transaction(TransactionType.CLOSE_DEPOSIT) {
    override fun execute() {
        product = closedDeposit
        closedDeposit.close()
        updateDescription()
        updateTransactionHistory()
    }

    private fun updateDescription() {
        description = "Deposit was closed. Calculated interest: ${closedDeposit.calculatedInterest}"
    }

    private fun updateTransactionHistory() {
        closedDeposit.addToTransactionHistory(this)
        closedDeposit.getAssociatedAccount().addToTransactionHistory(this)
    }
}
