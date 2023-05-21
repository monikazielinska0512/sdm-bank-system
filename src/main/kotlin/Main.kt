import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Deposit
import transactions.concrete_transactions.TakeLoan
import transactions.concrete_transactions.Transfer
import transactions.concrete_transactions.Withdrawal
import transactions.concrete_transactions.account.CloseAccount
import transactions.concrete_transactions.account.OpenAccount
import transactions.concrete_transactions.account.SwitchToDebitAccount
import transactions.concrete_transactions.deposit.CloseDeposit
import transactions.concrete_transactions.deposit.OpenDeposit
import java.time.LocalDate
import java.time.Period

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val bank = Bank();

        // Account creation
        val monikaAccount =
            Account("Monika", LocalDate.now(), 1000.0, InterestAlgorithm2())
        bank.executeCommand(OpenAccount(monikaAccount))

        val przemekAccount =
            Account("Przemek", LocalDate.now(), 0.0, InterestAlgorithm2())
        bank.executeCommand(OpenAccount(przemekAccount))


        //Deposit creation
        bank.executeCommand(OpenDeposit(monikaAccount, Period.ofYears(2)))

        //Taking loan
        bank.executeCommand(TakeLoan(przemekAccount, 100000.0))

        //Transfer between two accounts
        bank.executeCommand(Transfer(monikaAccount, przemekAccount, 100.0))

        //Deposit transfer
        bank.executeCommand(
            Transfer(
                monikaAccount,
                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit,
                100.0
            )
        )

        // Switch to a Debit account
        bank.executeCommand(SwitchToDebitAccount(monikaAccount))
        bank.executeCommand(Transfer(monikaAccount, przemekAccount, 100000.0))
        bank.executeCommand(Transfer(przemekAccount, monikaAccount, 100.0))


        //Withdrawal
        bank.executeCommand(Withdrawal(monikaAccount, 100.0))


        //Close deposit
        bank.executeCommand(CloseDeposit(monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit))

        //Close Account (with deposit)
        bank.executeCommand(CloseAccount(monikaAccount));

        // Bank History
        bank.getTransactionHistory().print()
    }
}
