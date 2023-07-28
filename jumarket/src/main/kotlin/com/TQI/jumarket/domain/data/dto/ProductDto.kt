package com.TQI.jumarket.domain.data.dto

import com.TQI.jumarket.domain.data.model.Category
import com.TQI.jumarket.domain.data.model.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProductDto(
    var id: Long,

    @field:NotBlank(message = "Invalid Input")
    var name: String = " ",

    @field:NotBlank(message = "Invalid Input")
    var unit: String = " ",

    @field:NotNull(message = "Invalid Input")
    var price: Double,

    var category: Category,

    @field:NotBlank(message = "Invalid Input")
    var description: String
) {
    constructor(model: Product): this(
        id = model.id,
        name = model.name,
        unit = model.unit,
        price = model.price,
        category = model.category,
        description = model.description
    )
    fun toEntity() : Product = Product(
            id = this.id,
            name = this.name,
            unit = this.unit,
            price = this.price,
            category = this.category,
            description = this.description
        )
}