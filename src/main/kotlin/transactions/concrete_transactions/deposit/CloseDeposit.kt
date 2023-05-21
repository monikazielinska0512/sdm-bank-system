package transactions.concrete_transactions.deposit

import products.Deposit
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class CloseDeposit(private var deposit: Deposit) : Transaction(TransactionType.CLOSE_DEPOSIT, LocalDate.now()) {
    override fun execute() {
        if (deposit.getAssociatedAccount().associatedProducts["deposits"]?.contains(deposit) == true) {
            deposit.close()
            deposit.getAssociatedAccount().associatedProducts["deposits"]?.remove(deposit)
            description =
                " Deposit_id: ${deposit.getId()}, Owner: ${deposit.getOwner()}, Date: ${LocalDate.now()}"
            deposit.addToTransactionHistory(this)
            deposit.getAssociatedAccount().addToTransactionHistory(this)
        }
        else{
            throw Exception("Deposit is not associated with this account")
        }
    }
}