package transactions.concrete_transactions

import products.Account
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Withdrawal(var account: Account, var amount: Double) : Transaction(TransactionType.WITHDRAWAL, LocalDate.now()) {
    override fun execute() {
        return account.removeMoney(amount)
    }
}