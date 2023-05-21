package transactions.concrete_transactions

import products.Deposit
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class CloseDeposit(private var deposit: Deposit) : Transaction(TransactionType.CLOSE_DEPOSIT, LocalDate.now()) {
    override fun execute() {
        deposit.close()
        deposit.addToTransactionHistory(this)
        deposit.getAssociatedAccount().addToTransactionHistory(this)
    }
}