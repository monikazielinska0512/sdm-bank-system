package reporting

import bank.Customer
import products.Account
import products.Deposit
import products.Loan

class LoanReportVisitor: ReportVisitor {
    private val loanList = mutableListOf<Loan>()

    //getter
    fun getLoanList(): List<Loan> {
        return loanList
    }
    override fun visit(loan: Loan) {
        loanList.add(loan)
    }

    override fun visit(account: Account){}
    override fun visit(deposit: Deposit){}
    override fun visit(customer: Customer){}

    override fun generateReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Loan List Report///\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")
        formattedData.append("| Loan ID                                | Owner                        | Balance           | Date Opened    |\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        // bank.Customer data
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