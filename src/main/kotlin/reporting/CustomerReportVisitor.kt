package reporting

import bank.Customer
import products.Account
import products.Deposit
import products.Loan

class CustomerReportVisitor: ReportVisitor {
    private val customerList = mutableListOf<Customer>()

    //getter
    fun getCustomerList(): List<Customer> {
        return customerList
    }
    override fun visit(customer: Customer) {
        customerList.add(customer)
    }

    override fun visit(account: Account){}
    override fun visit(loan: Loan){}
    override fun visit(deposit: Deposit){}

    override fun generateReport(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///bank.Customer List Report///\n")
        formattedData.append("+----------------------------------------+----------------------+----------------------+\n")
        formattedData.append("| bank.Customer ID                            | First Name           | Last Name            |\n")
        formattedData.append("+----------------------------------------+----------------------+----------------------+\n")

        // bank.Customer data
        for (customer in customerList) {
            formattedData.append("| ${customer.id.padEnd(30)}   ")
            formattedData.append("| ${customer.firstName.padEnd(20)} ")
            formattedData.append("| ${customer.lastName.padEnd(20)} |\n")
        }

        // Footer
        formattedData.append("+----------------------------------------+----------------------+----------------------+\n")

        return formattedData.toString()
    }

}