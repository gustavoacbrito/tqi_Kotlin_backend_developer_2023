package com.TQI.jumarket.controller.dto

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.CartItem
import com.TQI.jumarket.domain.data.model.Product

data class CartItemDTO(
    var id: Long? = null,
    var product: Product,
    var quantity: Int,
    var totalItemsCost: Double,
    var cart: Cart
) {
    fun toEntity(cart: Cart): CartItem {
        return CartItem(
            id = this.id ?: 0,
            product = this.product,
            quantity = this.quantity,
            totalItemsCost = this.totalItemsCost,
            cart = this.cart
        )
    }
}
