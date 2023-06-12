import bank.Bank
import bank.Customer
import interestMechanisms.InterestAlgorithm1
import products.Account
import products.DebitAccountDecorator
import transactions.concrete_transactions.product.Transfer
import java.time.LocalDate

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val mediator = InterBankPaymentAgency()
        val bank = Bank("MyBank", mediator)
        val account1 = Account(Customer("John", "Smith", bank), LocalDate.now(), 1000.0, InterestAlgorithm1(), bank)
        val account2 = Account(Customer("John", "Smith", bank), LocalDate.now(), 1000.0, InterestAlgorithm1(), bank)
        val debitAccount = DebitAccountDecorator(account1, 1000.0)

        var transfer = Transfer(debitAccount, account2, 1100.0)

        bank.executeCommand(transfer)
        println("account1 " + account1.balance)
        println("account2 " + account2.balance)

    }
}
