package com.TQI.jumarket.domain.data.model

import com.TQI.jumarket.domain.Service.enums.PaymentOptionEnum
import jakarta.persistence.*
import org.springframework.data.annotation.ReadOnlyProperty
import java.time.LocalDateTime
@Entity(name = "TB_SALE")
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var paymentOptionEnum: PaymentOptionEnum,

    @OneToOne
    @JoinColumn(name = "cart_id")
    var cart: Cart,

    @field:ReadOnlyProperty
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
