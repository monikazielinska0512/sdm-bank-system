package transactions.concrete_transactions.account

import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class SwitchToDebitAccount(private val account: Account) :
    Transaction(TransactionType.DEBIT, LocalDate.now()) {
    override fun execute() {
        account.switchToDebit()
        description =
            "Account_id: ${account.getId()}, isDebit: ${account.getIsDebit()} Owner: ${account.getOwner()}, Balance: ${account.balance}"

    }
}