import products.Account
import transactions.Transaction
import transactions.TransactionType

class InterBankTransfer(
    var senderAccount: Account,
    var receiverAccount: Account,
    var amount: Double,
) : Transaction(TransactionType.INTERBANK) {
    override fun execute() {
        senderAccount.withdrawMoney(amount)
        receiverAccount.addMoney(amount)
        product = senderAccount
        description =
            "$amount transferred to ${receiverAccount.getId()}"
    }
}
