package com.TQI.jumarket.domain.data.model

import com.TQI.jumarket.domain.Service.enums.PaymentOptionEnum
import jakarta.persistence.*
import java.time.LocalDateTime
@Entity(name = "TB_SALE")
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var paymentOptionEnum: PaymentOptionEnum,

    @OneToOne(cascade = [CascadeType.ALL])
    var cart: Cart? = null,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
