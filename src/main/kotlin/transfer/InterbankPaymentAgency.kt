package mediator

import TransferTransaction
import products.Account
import transfer.Bank
import java.time.LocalDate

// Mediator interface
interface InterbankPaymentAgency {
    fun sendTransfer(
        senderBank: Bank,
        receiverBank: Bank,
        senderAccount: Account,
        receiverAccount: Account,
        amount: Double
    )
}

// Concrete mediator implementation
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
            LocalDate.now(),
            "Transfer from ${senderBank.name} to ${receiverBank.name}"
        )
        senderBank.executeCommand(transferTransaction)
        receiverBank.executeCommand(transferTransaction)
    }
}
