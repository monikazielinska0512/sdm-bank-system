package transactions.concrete_transactions

import products.Account
import products.Product
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class Withdrawal(var product: Product, private var amount: Double) :
    Transaction(TransactionType.WITHDRAWAL, LocalDate.now()) {
    override fun execute() {
        product.withdrawMoney(amount)
        description =
            "Account_id: ${product.getId()}, Amount: ${amount}, Owner: ${product.getOwner()}, Balance: ${product.balance}"
    }
}