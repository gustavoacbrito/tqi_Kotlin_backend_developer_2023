package com.TQI.jumarket.domain.Service

import com.TQI.jumarket.domain.data.model.Cart

interface CartService : CrudService<Cart, Long> {
    fun handleTotalPrice(cart: Cart): Double
    fun deleteItem(cartId: Long, productId: Long)
    fun addItem(cartId: Long, cartItemId: Long): Cart
}