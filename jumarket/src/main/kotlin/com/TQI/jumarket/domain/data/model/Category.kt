package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity(name = "TB_CATEGORY")
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    val id: Long,

    @field:NotBlank(message = "Invalid Input")
    @Column(nullable = false)
    var categoryName: String = " "

)
