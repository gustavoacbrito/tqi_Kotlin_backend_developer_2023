package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart, Long>
