package reporting

import Customer
import products.Account
import products.Deposit
import products.Loan
import products.Product
import transactions.Transaction

interface ReportVisitor {
    fun visit(customer: Customer)
    fun visit(account: Account)
    fun visit(deposit: Deposit)
    fun visit(loan: Loan)

}