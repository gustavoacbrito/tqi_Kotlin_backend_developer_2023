package com.TQI.jumarket.data.models

import jakarta.persistence.*
import java.math.BigDecimal
@Entity
data class CartItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @JoinColumn(nullable = false) @ManyToOne val cart: Cart? = null,
    @ManyToOne val product: Product,
    @Column(nullable = false) val quantity: Int,
    @Column(nullable = false) val totalItemsCost: Double
)

