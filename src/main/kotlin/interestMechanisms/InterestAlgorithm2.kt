package interestMechanisms

import InterestMechanism
import products.Product

class InterestAlgorithm2: InterestMechanism {
    override fun calculateInterest(product: Product) {
        println("Calculating interest using algorithm 2")
        val calculatedInterest = product.balance * 0.05
    }
}