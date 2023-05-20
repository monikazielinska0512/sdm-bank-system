package products

import transactions.TransactionHistory
import java.time.LocalDate
import java.util.*

class Deposit(
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory
) : Product(
    UUID.randomUUID().toString(), owner, dateOpened, balance, transactionHistory
)