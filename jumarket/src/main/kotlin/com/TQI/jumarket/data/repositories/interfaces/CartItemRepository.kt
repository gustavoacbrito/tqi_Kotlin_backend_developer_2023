package com.TQI.jumarket.data.repositories.interfaces

import com.TQI.jumarket.data.models.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long>
