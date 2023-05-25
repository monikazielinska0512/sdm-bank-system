package transactions.concrete_transactions.loan

import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Loan
import transactions.Transaction
import transactions.TransactionHistory
import transactions.TransactionType
import java.time.LocalDate
import java.time.Period

class TakeLoan(private var account: Account, private var amount: Double, private var period: Period) :
    Transaction(TransactionType.TAKE_LOAN) {
    override fun execute() {
        val loan = Loan(account.getOwner(), account, amount, period, LocalDate.now(), InterestAlgorithm2())
        product = loan
        loan.open(account, amount)
        description =
            "Loan was taken. Loan_value: $amount"
        account.addToTransactionHistory(this)
        loan.addToTransactionHistory(this)
    }
}