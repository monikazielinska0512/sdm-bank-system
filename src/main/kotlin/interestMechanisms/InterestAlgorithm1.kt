package interestMechanisms

import InterestMechanism
import products.Product

class InterestAlgorithm1: InterestMechanism {
    override fun calculateInterest(product: Product): Double {
        return product.balance * 0.05
    }
}