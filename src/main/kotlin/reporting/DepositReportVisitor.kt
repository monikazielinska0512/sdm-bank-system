package reporting

import bank.Customer
import products.Account
import products.Deposit
import products.Loan

class DepositReportVisitor: ReportVisitor {
    private val depositList = mutableListOf<Deposit>()

    //getter
    fun getDepositList(): List<Deposit> {
        return depositList
    }
    override fun visit(deposit: Deposit) {
        depositList.add(deposit)
    }

    override fun visit(account: Account){}
    override fun visit(loan: Loan){}
    override fun visit(customer: Customer){}

    override fun generateReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Deposit List Report///\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")
        formattedData.append("| Deposit ID                             | Owner                       | Balance           | Date Opened    |\n")
        formattedData.append("+----------------------------------------+-----------------------------+--------------------+----------------+\n")

        // bank.Customer data
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

}