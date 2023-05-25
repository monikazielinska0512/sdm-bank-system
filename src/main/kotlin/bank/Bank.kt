package bank

import BankMediator
import InterestMechanism
import products.Account
import transactions.Transaction
import transactions.TransactionHistory
import transactions.TransactionType
import transactions.concrete_transactions.account.OpenAccount
import transactions.concrete_transactions.product.Transfer
import java.time.LocalDate

class Bank(
    val name: String,
    private var mediator: BankMediator,
    var entities: MutableList<BankEntity> = mutableListOf(),


    ) {
    private var transactionHistory: TransactionHistory = TransactionHistory()
    fun executeCommand(transaction: Transaction) {
        transaction.execute()
        transactionHistory.add(transaction)
    }
    fun getMediator(): BankMediator {
        return mediator
    }

    fun sendTransferToIBPA(transfer: Transfer) {
        mediator.addTransfer(transfer)
    }

    fun receiveInterBankTransferPackage(transfers: List<Transfer>){
        for (transfer in transfers){
            executeCommand(transfer)
        }
    }

    fun createAccount(owner: Customer, interestMechanism: InterestMechanism): Account {
        val account = Account(owner, LocalDate.now(), 0.0, interestMechanism, this)
        this.executeCommand(OpenAccount(account))
        return account
    }

    fun createCustomer(firstName: String, lastName: String): Customer {
        val customer = Customer(firstName, lastName, this)
        customer.addToBank()
        return customer
    }

    fun getTransactionHistory(): TransactionHistory {
        return transactionHistory
    }
}
