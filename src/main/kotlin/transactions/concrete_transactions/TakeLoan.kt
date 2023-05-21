package transactions.concrete_transactions

import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Loan
import transactions.Transaction
import transactions.TransactionHistory
import transactions.TransactionType
import java.time.LocalDate

class TakeLoan(private var account: Account, private var amount: Double) :
    Transaction(TransactionType.TAKE_LOAN, LocalDate.now()) {
    override fun execute() {
        val loan = Loan(account.getOwner(), account, amount, LocalDate.now(), InterestAlgorithm2())
        loan.open(account, amount)

        description =
            "Loan_id: ${loan.getId()}, Date: ${LocalDate.now()}, Loan_value: ${amount}, Owner: ${account.getOwner()} Account_balance: ${account.balance}"

        account.addToTransactionHistory(this)
        loan.addToTransactionHistory(this)
    }

}