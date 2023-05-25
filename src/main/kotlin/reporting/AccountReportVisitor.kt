package reporting

import bank.Customer
import products.Account
import products.Deposit
import products.Loan

class AccountReportVisitor: ReportVisitor {
    private val accountList = mutableListOf<Account>()

    fun getAccountList(): List<Account> {
        return accountList
    }

    override fun visit(account: Account) {
        accountList.add(account)
    }

    override fun visit(loan: Loan){}
    override fun visit(deposit: Deposit){}
    override fun visit(customer: Customer){}

    override fun generateReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Account List Report///\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")
        formattedData.append("| Account ID                            | Owner                        | Balance            | Date Opened    |\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        // bank.Customer data
        for (account in accountList) {
            formattedData.append("| ${account.getId().padEnd(30)}   ")
            formattedData.append("| ${(account.getOwner().firstName  + " " + account.getOwner().lastName).padEnd(25)}   ")
            formattedData.append("| ${account.balance.toString().padEnd(18)} ")
            formattedData.append("| ${account.getDateOpened().toString().padEnd(14)} |\n")
        }

        // Footer
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        return formattedData.toString()

    }
}