package interestMechanisms

import InterestMechanism
import products.Product

class InterestAlgorithm: InterestMechanism {
    override fun calculateInterest(product: Product) {
        println("Calculating interest using algorithm 1")
    }
}