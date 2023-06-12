package products

import bank.Customer
import InterestMechanism
import reporting.ReportVisitor
import bank.Bank
import java.time.LocalDate
import java.util.*

open class Account(
    accountOwner: Customer,
    openingDate: LocalDate,
    balance: Double,
    interestMechanism: InterestMechanism,
    bank: Bank
) : Product(
    UUID.randomUUID().toString(),
    accountOwner,
    openingDate,
    balance,
    interestMechanism,
    bank
) {
    val associatedProducts: Map<String, MutableList<Product>> = mapOf(
        "deposits" to mutableListOf(),
        "loans" to mutableListOf()
    )

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}
