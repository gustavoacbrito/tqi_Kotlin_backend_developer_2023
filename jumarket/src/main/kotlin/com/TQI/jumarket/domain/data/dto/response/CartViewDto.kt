package com.TQI.jumarket.domain.data.dto.response

import com.TQI.jumarket.domain.data.model.Cart

data class CartViewDto(
    var id: Long? = null,
    var totalSalePrice: Double?,
    var items: List<CartItemViewList> = mutableListOf()
) {
    constructor(model: Cart):this(
        id = model.id,
        totalSalePrice = model.totalSalePrice,
        items = model.items.map { CartItemViewList(it) }
    )
}
