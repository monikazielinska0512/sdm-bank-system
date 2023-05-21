package transactions.concrete_transactions

import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Withdrawal(var account: Account, private var amount: Double) : Transaction(TransactionType.WITHDRAWAL, LocalDate.now()) {
    override fun execute() {
        account.withdrawMoney(amount)
        description = "Account_id: ${account.getId()}, Amount: ${amount}, Owner: ${account.getOwner()}, Balance: ${account.balance}"
    }
}