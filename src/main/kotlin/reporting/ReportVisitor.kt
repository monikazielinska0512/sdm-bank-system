package reporting

import bank.Customer
import products.Account
import products.Deposit
import products.Loan

interface ReportVisitor {
    fun visit(customer: Customer)
    fun visit(account: Account)
    fun visit(deposit: Deposit)
    fun visit(loan: Loan)

    fun generateReport(): String
}