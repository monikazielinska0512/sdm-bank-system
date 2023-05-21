package products

import Customer
import InterestMechanism
import reporting.ReportVisitor
import java.time.LocalDate
import java.util.*

class Loan(
    owner: Customer,
    private var associatedAccount: Account,
    var value: Double,
    dateOpened: LocalDate,
    interestMechanism: InterestMechanism
) : Product(
    UUID.randomUUID().toString(), owner, dateOpened, 0.0, interestMechanism
) {

    fun open(account: Account, amount: Double) {
        this.associatedAccount = account
        this.associatedAccount.addMoney(amount)
        account.associatedProducts["loans"]?.add(this)
    }

    override fun accept(visitor: ReportVisitor) {
        visitor.visit(this)
    }
}