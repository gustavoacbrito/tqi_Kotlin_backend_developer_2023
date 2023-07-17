package com.TQI.jumarket.data.repositories.interfaces

import com.TQI.jumarket.data.models.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart, Long>
