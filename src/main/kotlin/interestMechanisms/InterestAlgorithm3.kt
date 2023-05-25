package interestMechanisms

import InterestMechanism
import products.Product

class InterestAlgorithm3 : InterestMechanism {
    override fun calculateInterest(product: Product): Double {
        return product.getTransactionHistory()
            .size() * 0.33f + product.balance * 0.09f + product.getDateOpened().year * 0.005f
    }
}