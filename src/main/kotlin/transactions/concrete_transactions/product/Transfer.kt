package transactions.concrete_transactions.product

import bank.Bank
import products.Product
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Transfer(
    private val sender: Product,
    private val receiver: Product,
    private val transferAmount: Double
) : Transaction(TransactionType.TRANSFER) {

    fun getSenderBank(): Bank {
        return sender.bank
    }

    fun getReceiverBank(): Bank {
        return receiver.bank
    }

    override fun execute() {
        product = sender
        sender.transfer(receiver, transferAmount)
        updateDescription()
        updateTransactionHistory()
    }

    private fun updateDescription() {
        description = "Sender: ${sender.getOwner().firstName}  Sender Balance: ${sender.balance} Sender: ${receiver.getOwner().firstName}  Receiver Balance: ${receiver.balance} "

    }

    private fun updateTransactionHistory() {
        sender.addToTransactionHistory(this)
        receiver.addToTransactionHistory(this)
    }
}

