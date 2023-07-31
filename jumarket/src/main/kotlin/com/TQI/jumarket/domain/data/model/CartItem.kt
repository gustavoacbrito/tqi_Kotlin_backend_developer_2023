package com.TQI.jumarket.domain.data.model


import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity(name = "TB_CART_ITEM")
data class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    val product: Product,

    @Column(nullable = false)
    var quantity: Int,

    @Column(nullable = false)
    var totalItemsCost: Double,

    @ManyToOne
    var cart: Cart
)

