package products

import transactions.TransactionHistory
import java.time.LocalDate

class Loan(
    id: String,
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory
) : Product(id, owner, dateOpened, balance, transactionHistory)