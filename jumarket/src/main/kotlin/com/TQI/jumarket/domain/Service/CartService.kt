package com.TQI.jumarket.domain.Service

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.CartItem
import com.TQI.jumarket.domain.data.model.Product

interface CartService : CrudService<Cart, Long> {
    fun handleTotalPrice(cart: Cart): Double
    fun removeItem(cartId: Long, productId: Long): Cart
    fun addItemToCart(cartId: Long,cartItem: CartItem): Cart
}