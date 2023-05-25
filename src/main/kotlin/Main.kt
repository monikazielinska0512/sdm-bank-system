import bank.Bank
import interestMechanisms.InterestAlgorithm2
import products.Deposit
import reporting.AccountReportVisitor
import reporting.CustomerReportVisitor
import transactions.concrete_transactions.account.CloseAccount
import transactions.concrete_transactions.account.SwitchToDebitAccount
import transactions.concrete_transactions.deposit.CloseDeposit
import transactions.concrete_transactions.deposit.OpenDeposit
import transactions.concrete_transactions.product.Transfer
import java.time.Period


object BankSystem {
    @JvmStatic
    fun main(args: Array<String>) {
        val paymentAgency = InterBankPaymentAgency()

        // Create banks
        val bankA = Bank("Bank A", paymentAgency)
        val bankB = Bank("Bank B", paymentAgency)

        // Register banks with the mediator
        paymentAgency.addBank(bankA)
        paymentAgency.addBank(bankB)

        val monika = bankA.createCustomer("Monika", "Zielinska")
        val przemek = bankB.createCustomer("Przemek", "Wozniak")

        // Account creation
        val monikaAccount = bankA.createAccount(monika, InterestAlgorithm2())
        val przemekAccount = bankB.createAccount(przemek, InterestAlgorithm2())

        monikaAccount.balance = 100.0
        przemekAccount.balance = 100.0

        println(monikaAccount.balance)
        println(przemekAccount.balance)

        // Send transfers using the mediator
//        bankA.sendTransfer(transfer1)
//        bankB.sendTransfer(transfer2)


//        val bankA = Bank("Bank A")
//        val bankB = Bank("Bank B")



//        // Switch to a Debit account
//        bankA.executeCommand(SwitchToDebitAccount(monikaAccount, -10000.0))
//        bankA.executeCommand(Transfer(monikaAccount, przemekAccount, 1000.0))
//        bankA.executeCommand(Transfer(przemekAccount, monikaAccount, 100.0))
//
//        //Withdrawal
////        bankA.executeCommand(Withdrawal(monikaAccount, 100.0))
//
//        //Close deposit
//        bankA.executeCommand(OpenDeposit(monikaAccount, Period.ofDays(30), InterestAlgorithm2()))
//        bankA.executeCommand(
//            Transfer(
//                monikaAccount,
//                monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit,
//                100.0
//            )
//        )
//
//        bankA.executeCommand(CloseDeposit(monikaAccount.associatedProducts["deposits"]?.get(0) as Deposit))
//
//        //Close Account (with deposit)
//        bankA.executeCommand(CloseAccount(monikaAccount));
//
//        println(bankA.entities)
//        println(bankB.entities)


        // Bank History
        println(bankA.getTransactionHistory().print())


        println(bankB.getTransactionHistory().print())


        // visitor


        val customerReportVisitor = CustomerReportVisitor()
        val accountReportVisitor = AccountReportVisitor()

        for (i in 1..10) {
            bankA.createCustomer("test$i", "test$i")
        }

        for (entity in bankA.entities) {
            entity.accept(accountReportVisitor)
        }

        println(accountReportVisitor.generateReport())

//        for (entity in bankA.entities) {
//            entity.accept(listReportVisitor)
//        }
//        println(listReportVisitor.generateCustomerReport())
//        println(listReportVisitor.generateAccountReport())
//        println(listReportVisitor.generateDepositReport())
//        println(listReportVisitor.generateLoanReport())
    }
}
