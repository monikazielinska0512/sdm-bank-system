package transactions.concrete_transactions.account

import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class CloseAccount(var account: Account) : Transaction(
    TransactionType.CLOSE_ACCOUNT,
    LocalDate.now(),
    "Account_id: ${account.getId()}, Owner: ${account.getOwner()},  Balance: ${account.balance}"
) {
    override fun execute() {
//        account.close()
        account.addToTransactionHistory(this)
    }
}