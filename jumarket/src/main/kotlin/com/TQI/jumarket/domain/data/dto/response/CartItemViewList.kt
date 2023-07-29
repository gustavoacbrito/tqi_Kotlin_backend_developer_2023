package com.TQI.jumarket.domain.data.dto.response

import com.TQI.jumarket.domain.data.model.CartItem

data class CartItemViewList(
    var productId: Long,
    var product: String,
    var quantity: Int,
    var unit: String,
    var totalItemsCost: Double

) {
    constructor(model: CartItem) : this(
        productId = model.product.id,
        product = model.product.name,
        quantity = model.quantity,
        unit = model.product.unit,
        totalItemsCost = model.totalItemsCost
    )
}