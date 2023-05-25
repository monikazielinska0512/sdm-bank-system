import bank.Bank
import products.Account
import products.Product
import transactions.Transaction
import transactions.TransactionType
import transactions.concrete_transactions.product.Transfer
import java.time.LocalDate

class InterBankTransfer(
    private val sender: Account,
    private val receiver: Account,
    private val transferAmount: Double
) : Transaction(TransactionType.INTERBANK) {

    private val senderBank: Bank = sender.bank
    private val receiverBank: Bank = receiver.bank
    override fun execute() {
        val transfer = Transfer(sender, receiver, transferAmount)
        product = sender
        senderBank.sendTransferToIBPA(transfer)
        updateDescription()
    }

    private fun updateDescription() {
        description =
            "Sender: ${sender.getOwner().firstName}  Sender Balance: ${sender.balance} Sender: ${receiver.getOwner().firstName}  Receiver Balance: ${receiver.balance} "
    }
}

