package com.TQI.jumarket.data.models

import jakarta.persistence.*

@Entity
data class Category(
    @Column(nullable = false) val categoryName: String = " ",
    @Column(nullable = false) @OneToMany(
        fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE, CascadeType.PERSIST], mappedBy = "category"
    ) val products: List<Product> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
