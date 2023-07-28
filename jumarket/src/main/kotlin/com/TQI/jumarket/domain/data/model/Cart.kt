package com.TQI.jumarket.domain.data.model

import jakarta.persistence.*

@Entity(name = "TB_CART")
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var totalSalePrice: Double? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE, CascadeType.PERSIST], mappedBy = "cart")
    var items: MutableList<CartItem> = mutableListOf(),

)

