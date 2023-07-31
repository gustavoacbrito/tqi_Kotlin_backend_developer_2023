package com.TQI.jumarket.domain.Service.Impl

import com.TQI.jumarket.domain.Service.CategoryService
import com.TQI.jumarket.domain.data.model.Category
import com.TQI.jumarket.domain.data.repositories.CategoryRepository
import com.TQI.jumarket.domain.exceptions.BusinessRulesException
import com.TQI.jumarket.domain.exceptions.EntityNotFoundException
import com.TQI.jumarket.domain.exceptions.ErrorMessages
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {
    override fun listCategoryByName(categoryName: String): List<Category> {
        return categoryRepository.findByCategoryNameContainingIgnoreCase(categoryName)
    }

    override fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun findById(id: Long): Category {
        val category: Optional<Category> = categoryRepository.findById(id)
        return category.orElseThrow { EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND) }
    }

    override fun create(@Valid model: Category): Category {
        if (categoryRepository.existsCategoryByCategoryName(model.categoryName)) {
            throw BusinessRulesException(ErrorMessages.CATEGORY_ALREADY_EXISTS)
        } else if (categoryRepository.existsById(model.id)) {
            throw BusinessRulesException(ErrorMessages.ID_ALREADY_EXISTS)
        }
        return categoryRepository.save(model)
    }

    override fun update(id: Long, model: Category): Category {
        if (!categoryRepository.findById(id).isPresent) {
            throw IllegalArgumentException(ErrorMessages.CANNOT_UPDATE_CATEGORY)
        }
        val dbCategory = this.findById(id)

        dbCategory.categoryName = model.categoryName

        return categoryRepository.save(dbCategory)
    }

    override fun delete(id: Long) {
        if (categoryRepository.findById(id).isPresent) {
            categoryRepository.deleteById(id)
        } else {
            throw EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND)
        }
    }
}