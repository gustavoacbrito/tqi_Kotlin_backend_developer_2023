package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*

@Entity(name = "TB_CATEGORY")
data class Category(
    @Column(nullable = false) var categoryName: String = " ",
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long
)
