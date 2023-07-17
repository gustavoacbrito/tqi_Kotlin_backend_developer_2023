package com.TQI.jumarket.domain.usecases

import com.TQI.jumarket.data.models.Product

class  GetCartUseCase {
        private val cart = mutableMapOf<Product, Int>()
        fun addItem(product: Product, quantity: Int) {
            val currentQuantity = cart.getOrDefault(product, 0)
            cart[product] = currentQuantity + quantity
        }

        fun removeItem(product: Product, quantity: Int) {
            val currentQuantity = cart.getOrDefault(product, 0)
            val newQuantity = currentQuantity - quantity
            if (newQuantity <= 0) {
                cart.remove(product)
            } else {
                cart[product] = newQuantity
            }
        }

        fun handleTotalPrice(): Double {
            var total = 0.0
            for ((product, quantity) in cart) {
                total += product.salePrice * quantity
            }
            return total
    }
}