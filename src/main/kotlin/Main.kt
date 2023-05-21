import products.Account
import products.Deposit
import transactions.TransactionHistory
import transactions.concrete_transactions.CloseAccount
import transactions.concrete_transactions.OpenAccount
import transactions.concrete_transactions.OpenDeposit
import java.time.LocalDate

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val account = Account("John Doe", LocalDate.now(), 0.0)
        val deposit = Deposit(account, 0.0, java.time.Duration.ofDays(30), "John Doe", LocalDate.now(), 0.0)
        val bank = Bank()
    }
}
