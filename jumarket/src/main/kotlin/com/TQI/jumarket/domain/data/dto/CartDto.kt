package com.TQI.jumarket.domain.data.dto

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.CartItem
import jakarta.validation.constraints.NotBlank

data class CartDto(

    var id: Long? = null,
    @field:NotBlank(message = "Invalid Input")
    var totalSalePrice: Double?,
) {
    constructor(model: Cart):this(
        id = model.id,
        totalSalePrice = model.totalSalePrice,

    )
    fun toEntity(): Cart = Cart(
            id = this.id ?: 0,
            totalSalePrice = this.totalSalePrice,

        )

}