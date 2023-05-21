import reporting.ReportVisitor

interface BankEntity {
    fun accept(visitor: ReportVisitor)
}