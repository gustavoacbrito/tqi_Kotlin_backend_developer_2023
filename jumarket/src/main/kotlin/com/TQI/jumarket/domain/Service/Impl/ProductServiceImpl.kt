package com.TQI.jumarket.domain.Service.Impl

import com.TQI.jumarket.domain.data.model.Product
import com.TQI.jumarket.domain.data.repositories.ProductRepository
import com.TQI.jumarket.domain.Service.ProductService
import com.TQI.jumarket.domain.exceptions.BusinessRulesException
import com.TQI.jumarket.domain.exceptions.EntityNotFoundException
import com.TQI.jumarket.domain.exceptions.ErrorMessages
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    override fun findById(id: Long): Product {
        val product: Optional<Product> = productRepository.findById(id)
        return product.orElseThrow { EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND) }
    }

    override fun create(model: Product): Product {
        if (productRepository.existsByName(model.name)) {
            throw BusinessRulesException(ErrorMessages.PRODUCT_ALREADY_EXISTS)
        } else if (productRepository.existsById(model.id)) {
            throw BusinessRulesException(ErrorMessages.ID_ALREADY_EXISTS)
        }
        return productRepository.save(model)
    }

    override fun update(id: Long, model: Product): Product {
        if (!productRepository.findById(id).isPresent) {
            throw IllegalArgumentException(ErrorMessages.CANNOT_UPDATE_PRODUCT)
        }
        val dbProduct = this.findById(id)

        dbProduct.name = model.name
        dbProduct.category = model.category
        dbProduct.price = model.price
        dbProduct.unit = model.unit
        dbProduct.description = model.description

        return productRepository.save(dbProduct)
    }

    override fun delete(id: Long) {
        if (productRepository.findById(id).isPresent) {
            productRepository.deleteById(id)
        } else {
            throw EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND)
        }
    }

    override fun listProductByName(productName: String): List<Product> {
        val products = productRepository.findByNameContainingIgnoreCase(productName)
        if (products.isEmpty()) {
            throw EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND)
        }
        return products
    }

    override fun listProductsByCategory(categoryId: Long): List<Product> {
        val products = productRepository.findByCategoryId(categoryId)
        if (products.isEmpty()) {
            throw EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND)
        }
        return products
    }

}