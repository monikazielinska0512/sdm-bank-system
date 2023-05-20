import products.Account
import transactions.TransactionHistory
import transactions.concrete_transactions.CloseAccount
import transactions.concrete_transactions.OpenAccount
import java.time.LocalDate

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val account = Account("John Doe", LocalDate.now(), 0.0, TransactionHistory())
        val bank = Bank()
        bank.executeCommand(OpenAccount(account));
        bank.executeCommand(CloseAccount(account));
    }
}
