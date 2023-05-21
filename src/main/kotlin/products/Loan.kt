package products


import InterestMechanism
import transactions.TransactionHistory
import java.time.LocalDate
import java.util.*

class Loan(
    owner: String,
    private var associatedAccount: Account,
    var value: Double,
    dateOpened: LocalDate,
    balance: Double,
      transactionHistory: TransactionHistory, interestMechanism: InterestMechanism
) : Product(id, owner, dateOpened, balance, transactionHistory, interestMechanism
)
{

    fun open(account: Account, amount: Double) {
        this.associatedAccount = account
        this.associatedAccount.addMoney(amount)
    }
}