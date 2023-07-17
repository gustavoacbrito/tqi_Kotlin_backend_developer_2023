package com.TQI.jumarket.domain.usecases

import com.TQI.jumarket.domain.usecases.enums.PaymentOptionEnum

class CloseSaleUseCase {
    fun closePurchase(cart: GetCartUseCase, paymentOptionEnum: PaymentOptionEnum): Double {
        val totalPurchase = cart.handleTotalPrice()
        val finalPrice = handleFinalPrice(totalPurchase, paymentOptionEnum)

        // Lógica para processar o pagamento

        // Lógica para finalizar a venda

        return finalPrice
    }
    private fun handleFinalPrice(totalPurchase: Double, paymentOptionEnum: PaymentOptionEnum): Double {
        return when (paymentOptionEnum) {
            PaymentOptionEnum.DINHEIRO, PaymentOptionEnum.PIX -> totalPurchase
            PaymentOptionEnum.CREDITO -> totalPurchase * 1.05 // Aplica uma taxa de 5% para pagamentos com cartão de crédito
            PaymentOptionEnum.DEBITO ->totalPurchase * 1.02 // Aplica uma taxa de 2% para pagamentos com cartão de débito
        }
    }
}