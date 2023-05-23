import reporting.ReportVisitor

class Customer(
    var id: String,
    var firstName: String,
    var lastName: String,
): BankEntity{
    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}