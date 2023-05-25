package bank

import reporting.ReportVisitor
import java.util.UUID.randomUUID

class Customer(
    var firstName: String,
    var lastName: String, override val bank: Bank,
) : BankEntity {
    var id: String = randomUUID().toString()
    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }

    override fun addToBank() {
        bank.entities.add(this)
    }

    override fun removeFromBank() {
        bank.entities.remove(this)
    }
}