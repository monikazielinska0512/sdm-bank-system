import interestMechanisms.InterestAlgorithm1
import interestMechanisms.InterestAlgorithm2
import products.Account
import transactions.TransactionHistory
import transactions.account.CloseAccount
import transactions.account.OpenAccount
import java.time.LocalDate


object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val account = Account("1", "John Doe", LocalDate.now(), 0.0, TransactionHistory(), InterestAlgorithm1())
        val account2 = Account("2", "Monika Zielińska", LocalDate.now(), 100.0, TransactionHistory(), InterestAlgorithm2())
        val bank = Bank()

        val closeAccount = CloseAccount(account)
        bank.takeCommand(closeAccount)
        account.addToTransactionHistory(closeAccount)

        val openAccount = OpenAccount(account)
        bank.takeCommand(openAccount)
        account.addToTransactionHistory(openAccount)

        account.calculateInterest()
        account2.calculateInterest()

        bank.executeCommands()
    }
}
