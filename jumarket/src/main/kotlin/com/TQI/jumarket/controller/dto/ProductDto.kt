package com.TQI.jumarket.controller.dto

import com.TQI.jumarket.domain.data.model.Category
import com.TQI.jumarket.domain.data.model.Product

data class ProductDTO(
    var id: Long,
    var name: String = " ",
    var unit: String = " ",
    var price: Double,
    var category: Category,
    var description: String
) {
    fun toEntity() : Product = Product(
            id = this.id,
            name = this.name,
            unit = this.unit,
            price = this.price,
            category = this.category,
            description = this.description
        )
}