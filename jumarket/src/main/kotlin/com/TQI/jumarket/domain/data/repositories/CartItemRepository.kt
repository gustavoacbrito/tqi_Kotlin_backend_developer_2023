package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long>{
    @Query("SELECT id FROM tb_cart_item WHERE product_id = :productId", nativeQuery = true)
    fun findByProductId(productId : Long): Long
}
