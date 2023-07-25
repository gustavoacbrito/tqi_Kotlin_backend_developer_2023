package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*

@Entity(name = "TB_CATEGORY")

data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var categoryName: String = " "

)
