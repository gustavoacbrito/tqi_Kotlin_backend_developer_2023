package com.TQI.jumarket.domain

import com.TQI.jumarket.data.models.Product
import com.TQI.jumarket.data.repositories.interfaces.ProductRepository
import com.TQI.jumarket.domain.usecases.RegisterProductUseCase

class RegisterProductUseCaseImpl(private val productRepository: ProductRepository) : RegisterProductUseCase {

    override fun listAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun createProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun updateProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: Long) {
        TODO("Not yet implemented")
    }

    override fun listProductById(productId: Long): Product? {
        TODO("Not yet implemented")
    }

    override fun listProductByName(productName: String): List<Product> {
        TODO("Not yet implemented")
    }

    override fun listProductsByCategory(categoryId: Long): List<Product> {
        TODO("Not yet implemented")
    }

}