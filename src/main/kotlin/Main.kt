import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Deposit
import transactions.concrete_transactions.loan.TakeLoan
import transactions.concrete_transactions.Transfer
import transactions.concrete_transactions.Withdrawal
import transactions.concrete_transactions.account.CloseAccount
import transactions.concrete_transactions.account.OpenAccount
import transactions.concrete_transactions.account.SwitchToDebitAccount
import transactions.concrete_transactions.deposit.CloseDeposit
import transactions.concrete_transactions.deposit.OpenDeposit
import transfer.Bank
import java.time.LocalDate
import java.time.Period

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val bankA = Bank("Bank A", InterbankPaymentAgencyImpl())
        val bankB = Bank("Bank B", InterbankPaymentAgencyImpl())


        // Account creation
        val monikaAccount =
            Account("Monika", LocalDate.now(), 1000000.0, InterestAlgorithm2())
        bankA.executeCommand(OpenAccount(monikaAccount))

        val przemekAccount =
            Account("Przemek", LocalDate.now(), 0.0, InterestAlgorithm2())
        bankB.executeCommand(OpenAccount(przemekAccount))

        // Bank A sends a transfer to Bank B
        bankA.sendTransfer(bankA, bankB, monikaAccount, przemekAccount, 100.0)

        // The transfer is executed by the mediator

        // Retrieve transaction history from Bank A or Bank B
        monikaAccount.getTransactionHistory().print()
        przemekAccount.getTransactionHistory().print()



//        //Deposit creation
//        bank.executeCommand(OpenDeposit(monikaAccount, Period.ofYears(2)))
//
//        //Taking loan
//        bank.executeCommand(TakeLoan(przemekAccount, 1000.0))
//
//        //Transfer between two accounts
//        bank.executeCommand(Transfer(monikaAccount, przemekAccount, 100.0))
//
//        //Transfer to deposit
//        bank.executeCommand(
//            Transfer(
//                monikaAccount,
//                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit,
//                100.0
//            )
//        )
//
//        //Transfer from deposit
//        bank.executeCommand(
//            DepositTransfer(
//                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit
//            )
//        )
//
//        // Switch to a Debit account
//        bank.executeCommand(SwitchToDebitAccount(monikaAccount, -10000.0))
//        bank.executeCommand(Transfer(monikaAccount, przemekAccount, 1000.0))
//        bank.executeCommand(Transfer(przemekAccount, monikaAccount, 100.0))
//
//
//        //Withdrawal
//        bank.executeCommand(Withdrawal(monikaAccount, 100.0))
//
//
//        //Close deposit
//        bank.executeCommand(CloseDeposit(monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit))
//
//        //Close Account (with deposit)
//        bank.executeCommand(CloseAccount(monikaAccount));
//
//        // transfer.Bank History
//        bank.getTransactionHistory().print()
    }
}
