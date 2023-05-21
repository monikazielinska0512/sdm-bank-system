interface InterBankMediator {
    fun transferMoney(senderBank: Bank, recipientBank: Bank, amount: Double)
}

class InterBankPaymentAgency : InterBankMediator {
    override fun transferMoney(senderBank: Bank, recipientBank: Bank, amount: Double, ) {
        println("Transferring $amount from ${senderBank.name} to ${recipientBank.name}")
    }
}

fun main() {
    val mediator = InterBankPaymentAgency()

    val bank1 = Bank("Bank 1", mediator)
    val bank2 = Bank("Bank 2", mediator)

    bank1.sendMoneyToOtherBank(bank2, 1000.0)
}
