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

    fun size(): Int {
        return history.size
    }

    fun getHistory(): Stack<Transaction> {
        return history
    }



    fun print(): String {
        val formattedData = StringBuilder()

        // Header
        formattedData.append("\n///Transaction History///\n")
        formattedData.append("+-----------------+------------+--------------------------------------+------------+--------------------------------------------------------------------------------------+\n")
        formattedData.append("| Type            | Date       | Product ID                           | Owner      | Description                                                                          |\n")
        formattedData.append("+-----------------+------------+--------------------------------------+------------+--------------------------------------------------------------------------------------+\n")

        // bank.Customer data
        for (transaction in history) {
            formattedData.append("| ${transaction.type.toString().padEnd(13)}   ")
            formattedData.append("| ${transaction.executionDate.toString().padEnd(10)} ")
            formattedData.append("| ${transaction.product?.getId()?.padEnd(30)} ")
            formattedData.append("| ${transaction.product?.getOwner()?.firstName?.padEnd(10)} ")
            formattedData.append("| ${transaction.description.toString().padEnd(84)} |\n")
        }
        // Footer
        formattedData.append("+----------------------------------------+----------------------+---------------------------------------------------------------------------------------------------------+\n")
        return formattedData.toString()
    }


}