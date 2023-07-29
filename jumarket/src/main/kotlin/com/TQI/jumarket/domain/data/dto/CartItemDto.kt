package com.TQI.jumarket.domain.data.dto

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.CartItem
import com.TQI.jumarket.domain.data.model.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CartItemDto(
    var id: Long? = null,

    var product: Product,

    @field:NotBlank(message = "Invalid Input")
    var quantity: Int,

    @field:NotBlank(message = "Invalid Input")
    var totalItemsCost: Double,

    @field:NotNull(message = "Invalid Input")
    var cartId: Long

) {
    fun toEntity(): CartItem {
        return CartItem(
            id = this.id ?: 0,
            product = this.product,
            quantity = this.quantity,
            totalItemsCost = this.totalItemsCost,
            cart = Cart(id = this.cartId)
        )
    }
}
