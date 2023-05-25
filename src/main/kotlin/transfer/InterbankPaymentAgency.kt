import products.Account
import bank.Bank
import transactions.concrete_transactions.product.Transfer
import transfer.BankMediator


class InterbankPaymentAgency : BankMediator {
    lateinit var packageBuffer: ArrayList<InterBankTransfer>

    override fun sendPackageOfTransactions(sender: Bank, packageBuffer: ArrayList<InterBankTransfer>) {

    }

    fun handlePackageDistribution() {
        if (packageBuffer.size >= 5) {
            for (transfer in packageBuffer) {
                transfer.senderAccount.bank.executeCommand(
                    Transfer(
                        transfer.senderAccount,
                        transfer.receiverAccount,
                        transfer.amount
                    )
                )
            }
            packageBuffer.clear()
        }
    }

    fun receiveTransferFromBank(
        senderAccount: Account,
        receiverAccount: Account,
        amount: Double
    ) {
        packageBuffer.add(InterBankTransfer(senderAccount, receiverAccount, amount))
        handlePackageDistribution()
    }


}
