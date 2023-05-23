package transfer
import InterBankTransfer
import bank.Bank

interface BankMediator {
    fun sendPackageOfTransactions(sender: Bank, packageBuffer: ArrayList<InterBankTransfer>)
}