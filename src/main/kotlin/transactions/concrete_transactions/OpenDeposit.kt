package transactions.concrete_transactions

import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Deposit
import transactions.Transaction
import transactions.TransactionType
import java.time.Duration
import java.time.LocalDate

class OpenDeposit(private var account: Account, private var period: Duration) :
    Transaction(TransactionType.OPEN_DEPOSIT, LocalDate.now()) {
    override fun execute() {
        val deposit = Deposit(account, 0.0, period, account.getOwner(), LocalDate.now(), 0.0, InterestAlgorithm2())
        account.addToTransactionHistory(this)
        deposit.addToTransactionHistory(this)
        println("Open Deposit: ${account.getOwner()} opened their deposit.")
    }
}