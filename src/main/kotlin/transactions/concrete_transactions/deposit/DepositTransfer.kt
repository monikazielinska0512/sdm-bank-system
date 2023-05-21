import products.Account
import products.Deposit
import transactions.Transaction
import transactions.TransactionType
import java.time.LocalDate

class DepositTransfer(private val deposit: Deposit) :
    Transaction(
        TransactionType.DEPOSIT_TRANSFER, LocalDate.now()
    ) {
    override fun execute() {
        val accountBalanceBefore = deposit.getAssociatedAccount().balance
        deposit.transfer(deposit.getAssociatedAccount(), deposit.balance + deposit.calculatedInterest)
        description =
            "Account Balance before: $accountBalanceBefore Account Balance after: ${deposit.getAssociatedAccount().balance} Calculated interest: ${deposit.calculatedInterest} Sender owner: ${deposit.getOwner()}"
        deposit.addToTransactionHistory(this)
        deposit.getAssociatedAccount().addToTransactionHistory(this)
    }
}