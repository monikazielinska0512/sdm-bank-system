import interestMechanisms.InterestAlgorithm2
import products.Deposit
import reporting.AccountReportVisitor
import reporting.CustomerReportVisitor
import transactions.concrete_transactions.product.Transfer
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
        val bankA = Bank("Bank A", InterbankPaymentAgency())
        val bankB = Bank("Bank B", InterbankPaymentAgency())

        val monika = bankA.createCustomer("Monika", "Zielinska")
        val przemek = bankB.createCustomer("Przemek", "Wozniak")

        // Account creation
        val monikaAccount = bankA.createAccount(monika, InterestAlgorithm2())
        val przemekAccount = bankB.createAccount(przemek, InterestAlgorithm2())

        // Bank A sends a transfer to Bank B
//        bankA.sendTransfer(bankA, bankB, monikaAccount, przemekAccount, 100.0)

        // The transfer is executed by the mediator

        // Retrieve transaction history from Bank A or Bank B
        monikaAccount.getTransactionHistory().print()
        przemekAccount.getTransactionHistory().print()

        // Switch to a Debit account
        bankA.executeCommand(SwitchToDebitAccount(monikaAccount, -10000.0))
        bankA.executeCommand(Transfer(monikaAccount, przemekAccount, 1000.0))
        bankA.executeCommand(Transfer(przemekAccount, monikaAccount, 100.0))

        //Withdrawal
//        bankA.executeCommand(Withdrawal(monikaAccount, 100.0))

        //Close deposit
        bankA.executeCommand(OpenDeposit(monikaAccount, Period.ofDays(30), InterestAlgorithm2()))
        bankA.executeCommand(
            Transfer(
                monikaAccount,
                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit,
                100.0
            )
        )

        bankA.executeCommand(CloseDeposit(monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit))

        //Close Account (with deposit)
        bankA.executeCommand(CloseAccount(monikaAccount));

        println(bankA.entities)
        println(bankB.entities)


        // Bank History
        println(bankA.getTransactionHistory().print())



        // visitor


        val customerReportVisitor = CustomerReportVisitor()
        val accountReportVisitor = AccountReportVisitor()

        for (i in 1..10) {
            bankA.createCustomer("test$i", "test$i")
        }

//        for (entity in bankA.entities) {
//            entity.accept(listReportVisitor)
//        }
//        println(listReportVisitor.generateCustomerReport())
//        println(listReportVisitor.generateAccountReport())
//        println(listReportVisitor.generateDepositReport())
//        println(listReportVisitor.generateLoanReport())
    }
}
