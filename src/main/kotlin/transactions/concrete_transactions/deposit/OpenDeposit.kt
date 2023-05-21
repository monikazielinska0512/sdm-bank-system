package transactions.concrete_transactions.deposit

import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Deposit
import transactions.Transaction
import transactions.TransactionType
import java.time.Duration
import java.time.LocalDate
import java.time.Period

class OpenDeposit(private var account: Account, private var period: Period) :
    Transaction(
        TransactionType.OPEN_DEPOSIT, LocalDate.now()
    ) {
    override fun execute() {
        val deposit = Deposit(account, 0.0, period, account.getOwner(), LocalDate.now(), 0.0, InterestAlgorithm2())
        deposit.open()
        description =
            "Deposit_id: ${deposit.getId()}, Date: ${LocalDate.now()}, Deposit_balance: ${deposit.balance}, Owner: ${account.getOwner()}"
        deposit.addToTransactionHistory(this)
        account.addToTransactionHistory(this)
    }
}