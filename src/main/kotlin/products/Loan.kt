package products

import transactions.TransactionHistory
import java.time.LocalDate
import java.util.*

class Loan(
    owner: String,
    var associatedAccount: Account,
    var value: Double,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory
) : Product(UUID.randomUUID().toString(), owner, dateOpened, balance, transactionHistory){




}