package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity(name = "TB_PRODUCT")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotBlank(message = "Invalid Input")
    @Column(nullable = false)
    var name : String = " ",

    @field:NotBlank(message = "Invalid Input")
    @Column(nullable = false)
    var unit : String = " ",

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be a positive value")
    @field:NotNull(message = "Invalid Input")
    @Column(nullable = false)
    var price : Double,


    @JoinColumn(nullable = false)
    @ManyToOne
    var category : Category,

    @Column(nullable = false)
    var description: String
)
