package transactions.concrete_transactions

import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class TransferToDeposit(private var account: Account, private var amount: Double) :
    Transaction(TransactionType.TRANSFER_TO_DEPOSIT, LocalDate.now()) {
    override fun execute() {
        account.transferToDeposit(amount)
        account.getTransactionHistory().add(this)
        account.getDeposit()?.getTransactionHistory()?.add(this)
        println(" ${account.getOwner()} transferred ${amount} to their deposit.")
    }
}