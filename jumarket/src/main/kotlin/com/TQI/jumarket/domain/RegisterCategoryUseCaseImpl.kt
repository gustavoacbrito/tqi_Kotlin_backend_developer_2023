package com.TQI.jumarket.domain

import com.TQI.jumarket.data.models.Category
import com.TQI.jumarket.data.repositories.interfaces.CategoryRepository
import com.TQI.jumarket.domain.usecases.RegisterCategoryUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegisterCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) : RegisterCategoryUseCase {
    override fun listAllCategories(): List<Category> {
        TODO("Not yet implemented")
    }

    override fun createCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(categoryId: Long) {
        TODO("Not yet implemented")
    }

    override fun listCategoryById(categoryId: Long): Category? {
        TODO("Not yet implemented")
    }

    override fun listCategoryByName(categoryName: String): List<Category> {
        TODO("Not yet implemented")
    }

}