package com.TQI.jumarket.domain.Service

import com.TQI.jumarket.domain.data.model.Product



interface ProductService : CrudService<Product, Long> {
    fun listProductByName(name: String): List<Product>
    fun listProductsByCategory(categoryId: Long): List<Product>
}