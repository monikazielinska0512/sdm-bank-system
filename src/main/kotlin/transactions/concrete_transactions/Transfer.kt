package transactions.concrete_transactions

import products.Product
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Transfer(private val sender: Product, private val receiver: Product, private val amount: Double) :
    Transaction(
        TransactionType.TRANSFER, LocalDate.now()) {
    override fun execute() {
        sender.transfer(receiver, amount)
        description =
            "Amount: $amount Sender owner: ${sender.getOwner()}, Sender_balance: ${sender.balance}" +
                    " Receiver owner: ${receiver.getOwner()}, Reciver_balance: ${receiver.balance}"
        sender.addToTransactionHistory(this)
        receiver.addToTransactionHistory(this)
    }
}