package com.TQI.jumarket.data.models

import jakarta.persistence.*

@Entity
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @OneToOne val sale: Sale? = null,
    @Column(nullable = false) @OneToMany(
        fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE, CascadeType.PERSIST], mappedBy = "cart"
    ) val items: List<CartItem> = mutableListOf(),
    @Column(nullable = false) val totalSalePrice: Double
)

