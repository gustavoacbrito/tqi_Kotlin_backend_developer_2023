package com.TQI.jumarket.domain.data.dto

import com.TQI.jumarket.domain.data.model.Category
import jakarta.validation.constraints.NotBlank

data class CategoryDto(

    var id: Long,

    @field:NotBlank(message = "Invalid Input")
    var categoryName: String

) {
    constructor(model: Category): this(
        id = model.id,
        categoryName = model.categoryName
    )

    fun toEntity(): Category = Category(
        id = this.id,
        categoryName = this.categoryName
    )

}