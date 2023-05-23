package interestMechanisms

import InterestMechanism
import products.Product

class InterestAlgorithm2: InterestMechanism {
    override fun calculateInterest(product: Product): Double {
        return product.balance * 0.1
    }
}