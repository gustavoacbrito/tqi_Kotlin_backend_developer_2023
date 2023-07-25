package com.TQI.jumarket.controller.dto

import com.TQI.jumarket.domain.Service.enums.PaymentOptionEnum
import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.Sale
import java.time.LocalDateTime

data class SaleDto(
    val id: Long,
    val paymentOptionEnum: PaymentOptionEnum,
    val cart: Cart,
    val createdAt: LocalDateTime
){
    fun toEntity(): Sale {
        return Sale(
            id = this.id,
            paymentOptionEnum = this.paymentOptionEnum,
            cart = this.cart,
            createdAt = this.createdAt
        )
    }
}
