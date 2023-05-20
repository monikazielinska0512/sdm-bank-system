import products.Account
import transactions.TransactionHistory
import transactions.account.CloseAccount
import transactions.account.OpenAccount
import transactions.account.Withdrawl
import java.time.LocalDate

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val account = Account("John Doe", LocalDate.now(), 0.0, TransactionHistory())
        val account2 = Account( "Monika Zielińska", LocalDate.now(), 100.0, TransactionHistory())
        val bank = Bank()

        val closeAccount = CloseAccount(account)
        bank.takeCommand(closeAccount)
        bank.takeCommand(Withdrawl(account, 100.0))
        account.addToTransactionHistory(closeAccount)
        account.addToTransactionHistory(Withdrawl(account, 100.0))

        val openAccount = OpenAccount(account)
        bank.takeCommand(openAccount)
        account.addToTransactionHistory(openAccount)

        bank.executeCommands()
    }
}
