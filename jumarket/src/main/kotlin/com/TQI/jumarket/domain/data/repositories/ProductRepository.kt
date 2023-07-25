package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun existsByProductName(name: String): Boolean
    fun findByCategoryId(categoryId: Long): List<Product>
    fun findByProductNameContainingIgnoreCase(productName: String): List<Product>
    fun getProductPrice(productId: Long): Double
}
