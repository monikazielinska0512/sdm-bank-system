import bank.Bank
import transactions.concrete_transactions.product.Transfer

class InterBankPaymentAgency : BankMediator {
    private val banks: MutableList<Bank> = mutableListOf()
    private val transfers: MutableList<Transfer> = mutableListOf()
    override fun addTransfer(transfer: Transfer) {
        transfers.add(transfer)
        if (transfers.size == 5){
            sendPackage()
            transfers.clear()
        }
    }

    override fun addBank(bank: Bank) {
        banks.add(bank)
    }
    override fun sendPackage() {
            val transfersGroupedByReceiverBank = transfers.groupBy { transfer -> transfer.getReceiverBank() }
            transfersGroupedByReceiverBank.forEach { (bank, transfers) ->
                bank.receiveInterBankTransferPackage(transfers)
            }
    }
    override fun getTransfers(): MutableList<Transfer> {
        return transfers
    }
}
