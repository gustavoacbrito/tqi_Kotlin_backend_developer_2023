package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun existsByName(name: String): Boolean
    fun findByCategoryId(categoryId: Long): List<Product>
    fun findByNameContainingIgnoreCase(name: String): List<Product>
    @Query("SELECT price FROM tb_product WHERE id = :productId", nativeQuery = true)
    fun findPriceById(@Param("productId") productId: Long): Double
}
