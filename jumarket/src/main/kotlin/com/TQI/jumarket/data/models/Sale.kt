package com.TQI.jumarket.data.models

import com.TQI.jumarket.domain.usecases.enums.PaymentOptionEnum
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
@Entity
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val paymentOptionEnum: PaymentOptionEnum,

    @OneToOne(cascade = [CascadeType.ALL])
    val cart: Cart? = null,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
