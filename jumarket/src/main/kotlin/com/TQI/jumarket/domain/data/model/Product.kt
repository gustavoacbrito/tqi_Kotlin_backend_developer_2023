package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*

@Entity(name = "TB_PRODUCT")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var name : String = " ",

    @Column(nullable = false)
    val unit : String = " ",

    @Column(nullable = false)
    val price : Double,

    @JoinColumn(nullable = false)
    @ManyToOne
    val category : Category,

    @Column(nullable = false)
    val description: String
)
