package com.TQI.jumarket.domain.data.dto

import com.TQI.jumarket.domain.Service.enums.PaymentOptionEnum
import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.Sale
import com.TQI.jumarket.domain.exceptions.EntityNotFoundException
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.ReadOnlyProperty
import java.time.LocalDateTime

data class SaleDto(
    val id: Long,
    @field:NotBlank(message = "Invalid Input")
    val paymentOptionEnum: PaymentOptionEnum,
    @field:NotNull(message = "Invalid Input")
    val cartId: Long,
    @field:ReadOnlyProperty
    val createdAt: LocalDateTime = LocalDateTime.now()
){
    constructor(model: Sale): this(
        id = model.id,
        paymentOptionEnum = model.paymentOptionEnum,
        cartId = model.cart.id,
        createdAt = model.createdAt
    )
    fun toEntity(): Sale {
        return Sale(
            id = this.id,
            paymentOptionEnum = this.paymentOptionEnum,
            cart = Cart(id = this.cartId),
            createdAt = this.createdAt
        )
        }
    }

