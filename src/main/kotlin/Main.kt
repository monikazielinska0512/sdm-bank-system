import interestMechanisms.InterestAlgorithm2
import products.Account
import products.Deposit
import reporting.ListReportVisitor
import transactions.concrete_transactions.loan.TakeLoan
import transactions.concrete_transactions.Transfer
import transactions.concrete_transactions.Withdrawal
import transactions.concrete_transactions.account.CloseAccount
import transactions.concrete_transactions.account.OpenAccount
import transactions.concrete_transactions.account.SwitchToDebitAccount
import transactions.concrete_transactions.deposit.CloseDeposit
import transactions.concrete_transactions.deposit.OpenDeposit
import java.time.LocalDate
import java.time.Period
import java.util.*

object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val mediator = InterBankPaymentAgency()
        val bank = Bank("Bank 1", mediator)

        val monika = Customer(UUID.randomUUID().toString(), "Monika", "abc")
        val przemek = Customer(UUID.randomUUID().toString(), "Przemek", "abc")

        // Account creation
        val monikaAccount =
            Account(monika, LocalDate.now(), 1000.0, InterestAlgorithm2())
        bank.executeCommand(OpenAccount(monikaAccount))

        val przemekAccount =
            Account(przemek, LocalDate.now(), 0.0, InterestAlgorithm2())
        bank.executeCommand(OpenAccount(przemekAccount))

        bank.entities.add(monikaAccount)
        bank.entities.add(przemekAccount)


        //Deposit creation
        bank.executeCommand(OpenDeposit(monikaAccount, Period.ofYears(2)))

        //Taking loan
        bank.executeCommand(TakeLoan(przemekAccount, 1000.0))

        //Transfer between two accounts
        bank.executeCommand(Transfer(monikaAccount, przemekAccount, 100.0))

        //Transfer to deposit
        bank.executeCommand(
            Transfer(
                monikaAccount,
                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit,
                100.0
            )
        )

        //Transfer from deposit
        bank.executeCommand(
            DepositTransfer(
                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit
            )
        )

        // Switch to a Debit account
        bank.executeCommand(SwitchToDebitAccount(monikaAccount, -10000.0))
        bank.executeCommand(Transfer(monikaAccount, przemekAccount, 1000.0))
        bank.executeCommand(Transfer(przemekAccount, monikaAccount, 100.0))


        //Withdrawal
        bank.executeCommand(Withdrawal(monikaAccount, 100.0))


        //Close deposit
        bank.executeCommand(CloseDeposit(monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit))

        //Close Account (with deposit)
        bank.executeCommand(CloseAccount(monikaAccount));

        // Bank History
        bank.getTransactionHistory().print()

        // visitor

        val listReportVisitor = ListReportVisitor()

        for (i in 1..10) {
            val customer = Customer(UUID.randomUUID().toString(), "test$i", "test$i")
            bank.entities.add(customer)
        }

        for (entity in bank.entities) {
            entity.accept(listReportVisitor)
        }

        println(listReportVisitor.generateCustomerReport())
        println(listReportVisitor.generateAccountReport())
        println(listReportVisitor.generateDepositReport())
        println(listReportVisitor.generateLoanReport())
    }
}
