package products
import InterestMechanism
import transactions.TransactionHistory
import java.time.LocalDate

class Deposit(
    id: String,
    owner: String,
    dateOpened: LocalDate,
    balance: Double,
    transactionHistory: TransactionHistory, interestMechanism: InterestMechanism
) : Product(id, owner, dateOpened, balance, transactionHistory, interestMechanism
)