package transfer

import mediator.InterbankPaymentAgency
import products.Account
import transactions.Transaction
import transactions.TransactionHistory

class Bank(
    val name: String,
    private val interbankPaymentAgency: InterbankPaymentAgency
) {
    private var transactionHistory: TransactionHistory = TransactionHistory()
    fun executeCommand(transaction: Transaction) {
        transaction.execute()
        transactionHistory.add(transaction)
    }

    fun sendTransfer(
        senderBank: Bank,
        receiverBank: Bank,
        senderAccount: Account,
        receiverAccount: Account,
        amount: Double
    ) {
        interbankPaymentAgency.sendTransfer(senderBank, receiverBank, senderAccount, receiverAccount, amount)
    }

    fun getTransactionHistory(): TransactionHistory {
        return transactionHistory
    }
}
