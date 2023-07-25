package com.TQI.jumarket.controller.dto

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.Sale

data class CartDTO(
    var id: Long? = null,
    var sale: Sale,
    var totalSalePrice: Double,
    var items: List<CartItemDTO> = emptyList()
) {
    fun toEntity(): Cart {
        val cart = Cart(
            id = this.id ?: 0,
            sale = this.sale,
            totalSalePrice = this.totalSalePrice
        )
        cart.items = this.items.map { it.toEntity(cart) }.toMutableList()
        return cart
    }
}