package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category,Long>{
    fun findByCategoryNameIgnoreCase(categoryName: String): List<Category>
    fun existsCategoryByCategoryName(categoryName: String): Boolean
}