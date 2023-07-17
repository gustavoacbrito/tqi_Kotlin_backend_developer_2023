package com.TQI.jumarket.domain.usecases

import com.TQI.jumarket.data.models.Category

interface RegisterCategoryUseCase {
    fun listAllCategories(): List<Category>
    fun createCategory(category: Category)
    fun updateCategory(category: Category)
    fun deleteCategory(categoryId: Long)
    fun listCategoryById(categoryId: Long): Category?
    fun listCategoryByName(categoryName: String): List<Category>
}