package bank

import reporting.ReportVisitor

interface BankEntity {
    val bank: Bank
    fun accept(visitor: ReportVisitor)

    fun addToBank() {
        bank.entities.add(this)
    }
    fun removeFromBank() {
        bank.entities.remove(this)
    }
}