package transactions.concrete_transactions

import products.Account
import products.Loan
import transactions.Transaction
import transactions.TransactionHistory
import transactions.TransactionType
import java.time.LocalDate

class TakeLoan(private var account: Account, private var amount: Double) :
    Transaction(TransactionType.TAKE_LOAN, LocalDate.now()) {
    override fun execute() {
        val loan = Loan(account.getOwner(), account, amount, LocalDate.now(), 0.0)
        loan.open(account, amount)
        account.addToTransactionHistory(this)
        loan.addToTransactionHistory(this)
        println("Take Loan: ${account.getOwner()} took a loan.")
    }

}