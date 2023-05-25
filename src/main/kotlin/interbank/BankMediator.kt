import bank.Bank
import transactions.concrete_transactions.product.Transfer

interface BankMediator {
    fun sendPackage()
    fun addBank(bank: Bank)
    fun addTransfer(transfer: Transfer)
    fun getTransfers(): MutableList<Transfer>
}