package transactions
abstract class Transaction {
    // swoje atrybuty

    //konstruktor

    abstract fun execute()
}

// osobna klasa dla każdego rodzaje transakcji np. Transfer, Deposit, Withdrawal, Payment, etc. która nadpisuje metodę exectue()
