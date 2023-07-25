package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long>
