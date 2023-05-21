package transactions

import java.util.*

class TransactionHistory {
    private val history = Stack<Transaction>()
    fun add(c: Transaction) {
        history.push(c)
    }

    fun remove(): Transaction {
        return history.pop()
    }

    fun print() {
//        for (i in history) {
//            println(i.type.toString() + " " + i.executionDate + " " + i.description)
//        }
        val dash = "-".repeat(100)
        println(
            "Transaction Type${" ".repeat(5)} Execution Date${" ".repeat(15)} Description${" ".repeat(15)}"
        )
        println(dash)
        for (k in history) {
            println(
                k.type.toString().padEnd(22) +
                        k.executionDate.toString().trim().padEnd(30) +
                        k.description.toString().trim().padEnd(35)
            )
        }

    }


}