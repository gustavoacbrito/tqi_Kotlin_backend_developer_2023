package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*

@Entity(name = "TB_CART_ITEM")
data class CartItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @ManyToOne val product: Product,
    @Column(nullable = false) var quantity: Int,
    @Column(nullable = false) val totalItemsCost: Double
)

