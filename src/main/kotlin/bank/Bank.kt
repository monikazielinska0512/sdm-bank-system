package bank

import InterbankPaymentAgency
import InterestMechanism
import products.Account
import transactions.Transaction
import transactions.TransactionHistory
import transactions.concrete_transactions.account.OpenAccount
import java.time.LocalDate

class Bank(
    val name: String,
    private val interbankPaymentAgency: InterbankPaymentAgency,
    var entities: MutableList<BankEntity> = mutableListOf()
) {
    private var transactionHistory: TransactionHistory = TransactionHistory()
    fun executeCommand(transaction: Transaction) {
        transaction.execute()
        transactionHistory.add(transaction)
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

    fun sendTransfer(
        senderBank: Bank,
        receiverBank: Bank,
        senderAccount: Account,
        receiverAccount: Account,
        amount: Double
    ) {
//        interbankPaymentAgency.receiveTransferFromBank(senderBank, receiverBank, senderAccount, receiverAccount, amount)
    }

    fun getTransactionHistory(): TransactionHistory {
        return transactionHistory
    }
}
