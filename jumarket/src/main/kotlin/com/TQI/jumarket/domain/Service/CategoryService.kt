package com.TQI.jumarket.domain.Service

import com.TQI.jumarket.domain.data.model.Category

interface CategoryService : CrudService<Category, Long> {
    fun listCategoryByName(categoryName: String): List<Category>
}