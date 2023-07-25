package com.TQI.jumarket.domain.data.repositories

import com.TQI.jumarket.domain.data.model.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleRepository : JpaRepository<Sale, Long>
