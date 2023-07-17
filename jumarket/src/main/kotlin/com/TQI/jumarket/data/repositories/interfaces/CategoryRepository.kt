package com.TQI.jumarket.data.repositories.interfaces

import com.TQI.jumarket.data.models.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category,Long>