package reporting

import Customer
import products.Account
import products.Deposit
import products.Loan
import products.Product
import transactions.Transaction

class ListReportVisitor: ReportVisitor {
    private val customerList = mutableListOf<Customer>()
    private val accountList = mutableListOf<Account>()
    private val depositList = mutableListOf<Deposit>()
    private val loanList = mutableListOf<Loan>()
    override fun visit(customer: Customer) {
        customerList.add(customer)
    }

    override fun visit(account: Account) {
        accountList.add(account)
    }

    override fun visit(deposit: Deposit) {
        depositList.add(deposit)
    }

    override fun visit(loan: Loan) {
        loanList.add(loan)
    }

    fun generateCustomerReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Customer List Report///\n")
        formattedData.append("+----------------------------------------+----------------------+----------------------+\n")
        formattedData.append("| Customer ID                            | First Name           | Last Name            |\n")
        formattedData.append("+----------------------------------------+----------------------+----------------------+\n")

        // Customer data
        for (customer in customerList) {
            formattedData.append("| ${customer.id.padEnd(30)}   ")
            formattedData.append("| ${customer.firstName.padEnd(20)} ")
            formattedData.append("| ${customer.lastName.padEnd(20)} |\n")
        }

        // Footer
        formattedData.append("+----------------------------------------+----------------------+----------------------+\n")

        return formattedData.toString()
    }

    fun generateAccountReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Account List Report///\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")
        formattedData.append("| Account ID                            | Owner                        | Balance            | Date Opened    |\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        // Customer data
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

    fun generateDepositReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Deposit List Report///\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")
        formattedData.append("| Deposit ID                             | Owner                       | Balance           | Date Opened    |\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        // Customer data
        for (deposit in depositList) {
            formattedData.append("| ${deposit.getId().padEnd(30)}   ")
            formattedData.append("| ${(deposit.getOwner().firstName  + " " + deposit.getOwner().lastName).padEnd(25)}   ")
            formattedData.append("| ${deposit.balance.toString().padEnd(18)} ")
            formattedData.append("| ${deposit.getDateOpened().toString().padEnd(14)} |\n")
        }

        // Footer
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        return formattedData.toString()
    }

    fun generateLoanReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Loan List Report///\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")
        formattedData.append("| Loan ID                                | Owner                        | Balance           | Date Opened    |\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        // Customer data
        for (loan in loanList) {
            formattedData.append("| ${loan.getId().padEnd(30)}   ")
            formattedData.append("| ${(loan.getOwner().firstName  + " " + loan.getOwner().lastName).padEnd(25)}   ")
            formattedData.append("| ${loan.balance.toString().padEnd(18)} ")
            formattedData.append("| ${loan.getDateOpened().toString().padEnd(14)} |\n")
        }

        // Footer
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        return formattedData.toString()
    }
}