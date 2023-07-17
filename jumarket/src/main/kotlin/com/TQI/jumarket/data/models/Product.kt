package com.TQI.jumarket.data.models

import jakarta.persistence.*

@Entity
data class Product(
    @Column(nullable = false) val product : String = " ",
    @Column(nullable = false) val unit : String = " ",
    @Column(nullable = false) val salePrice : Double,
    @JoinColumn(nullable = false) @ManyToOne val category : Category? = null,
    @Column(nullable = false) val description: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
