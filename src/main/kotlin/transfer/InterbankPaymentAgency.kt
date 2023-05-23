import products.Account
import transfer.Bank
import java.time.LocalDate

interface InterbankPaymentAgency {
    fun sendTransfer(
        senderBank: Bank,
        receiverBank: Bank,
        senderAccount: Account,
        receiverAccount: Account,
        amount: Double
    )
}

class InterbankPaymentAgencyImpl : InterbankPaymentAgency {
    override fun sendTransfer(
        senderBank: Bank,
        receiverBank: Bank,
        senderAccount: Account,
        receiverAccount: Account,
        amount: Double
    ) {
        val transferTransaction = TransferTransaction(
            senderAccount,
            receiverAccount,
            amount,
            LocalDate.now()
        )
        senderBank.executeCommand(transferTransaction)
        receiverBank.getTransactionHistory().add(transferTransaction)
        senderAccount.addToTransactionHistory(transferTransaction)
        receiverAccount.addToTransactionHistory(transferTransaction)
    }
}
