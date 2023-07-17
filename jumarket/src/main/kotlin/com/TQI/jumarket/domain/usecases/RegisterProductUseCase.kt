package com.TQI.jumarket.domain.usecases

import com.TQI.jumarket.data.models.Product

interface RegisterProductUseCase {
    fun createProduct(product: Product)
    fun listAllProducts(): List<Product>
    fun listProductById(productId: Long): Product?
    fun listProductByName(productName: String): List<Product>
    fun listProductsByCategory(categoryId: Long): List<Product>
    fun updateProduct(product: Product)
    fun deleteProduct(productId: Long)
}