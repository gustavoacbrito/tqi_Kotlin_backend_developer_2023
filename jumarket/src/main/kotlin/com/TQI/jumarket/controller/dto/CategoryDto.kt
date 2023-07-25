package com.TQI.jumarket.controller.dto

import com.TQI.jumarket.domain.data.model.Category

data class CategoryDto(
    var categoryName: String,
    var id: Long
) {
    fun toEntity(): Category = Category(
        id = this.id,
        categoryName = this.categoryName
    )

}