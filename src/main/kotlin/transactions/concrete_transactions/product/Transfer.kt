package transactions.concrete_transactions.product

import products.Product
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Transfer(
    private val sender: Product,
    private val receiver: Product,
    private val transferAmount: Double
) : Transaction(TransactionType.TRANSFER) {
    init {
        validateTransferAmount()
    }

    private fun validateTransferAmount() {
        if (transferAmount <= 0) {
            throw IllegalArgumentException("Transfer amount must be greater than zero.")
        }
    }

    override fun execute() {
        product = sender
        sender.transfer(receiver, transferAmount)
        updateDescription()
        updateTransactionHistory()
    }

    private fun updateDescription() {
        description = "$transferAmount was transferred to ${receiver.getId()}. Sender Balance: ${sender.balance}"
    }

    private fun updateTransactionHistory() {
        sender.addToTransactionHistory(this)
        receiver.addToTransactionHistory(this)
    }
}

