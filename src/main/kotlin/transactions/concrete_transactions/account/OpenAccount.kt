package transactions.concrete_transactions.account

import interestMechanisms.InterestAlgorithm2
import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class OpenAccount(val account: Account) : Transaction(
    TransactionType.OPEN_ACCOUNT, LocalDate.now(),
    "Account_id: ${account.getId()}, Balance: ${account.balance}, isDebit: ${account.getIsDebit()}, Owner: ${account.getOwner()}"
) {
    override fun execute() {
        account.addToTransactionHistory(this)
    }
}