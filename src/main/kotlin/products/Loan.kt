package products
import java.time.LocalDate
import java.util.*

class Loan(
    owner: String,
    private var associatedAccount: Account,
    var value: Double,
    dateOpened: LocalDate,
    balance: Double,
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance){

    fun open(account: Account, amount: Double) {
        this.associatedAccount = account
        this.associatedAccount.addMoney(amount)
    }
}