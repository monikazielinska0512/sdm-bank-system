package transactions.concrete_transactions.deposit

import InterestMechanism
import products.Account
import products.Deposit
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate
import java.time.Period

class OpenDeposit(
    private var account: Account,
    private var period: Period,
    private var interestAlgorithm: InterestMechanism
) :
    Transaction(
        TransactionType.OPEN_DEPOSIT
    ) {
    override fun execute() {
        val deposit = Deposit(account, 0.0, period, account.getOwner(), LocalDate.now(), 0.0,  interestAlgorithm)
        product = deposit
        deposit.open()
        description = "Deposit was opened. Deposit_balance: ${deposit.balance}"
        deposit.addToTransactionHistory(this)
        account.addToTransactionHistory(this)
    }
}