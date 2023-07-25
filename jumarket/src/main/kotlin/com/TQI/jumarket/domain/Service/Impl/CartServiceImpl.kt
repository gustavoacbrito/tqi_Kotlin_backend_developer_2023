package com.TQI.jumarket.domain.Service.Impl

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.repositories.CartRepository
import com.TQI.jumarket.domain.Service.CartService
import com.TQI.jumarket.domain.data.repositories.CartItemRepository
import com.TQI.jumarket.domain.data.repositories.ProductRepository
import com.TQI.jumarket.domain.exceptions.EntityNotFoundException
import com.TQI.jumarket.domain.exceptions.ErrorMessages
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
    private val productRepository: ProductRepository
) : CartService  {

    @Transactional(readOnly = true)
    override fun findAll(): List<Cart> {
        return cartRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Cart {
        return cartRepository.findById(id).orElseThrow { EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND) }
    }

    @Transactional
    override fun create(model: Cart): Cart {
        if (cartRepository.existsById(model.id)){
            throw IllegalArgumentException("Cart with id ${model.id} already exists")
        }

        return cartRepository.save(model)
    }

    @Transactional
    override fun update(id: Long, model: Cart): Cart {
        if (model.id != id){
            throw IllegalArgumentException("The cart to be updated must be the same")
        }

        val dbCart = this.findById(id)

        dbCart.items = model.items
        dbCart.sale = model.sale
        dbCart.totalSalePrice = model.totalSalePrice

        return cartRepository.save(dbCart)
    }

    @Transactional
    override fun delete(id: Long) {
        val dbCart = this.findById(id)
        return cartRepository.delete(dbCart)
    }

    override fun addItem(cartId: Long, cartItemId: Long): Cart {
        val cart = cartRepository.findById(cartId).orElseThrow { NoSuchElementException("Cart not found with id: $cartId") }
        val cartItem = cartItemRepository.findById(cartItemId).orElseThrow { NoSuchElementException("CartItem not found with id: $cartItemId") }

        val existingItem = cart.items.find { it.product.id == cartItem.product.id }
        if (existingItem != null){
            existingItem.quantity += cartItem.quantity
        } else {
            cart.items.add(cartItem)
        }

        cart.totalSalePrice = handleTotalPrice(cart)

        return cartRepository.save(cart)
    }

    override fun deleteItem(cartId: Long, productId: Long) {
        val cart = cartRepository.findById(cartId).orElseThrow { NoSuchElementException("Cart not found with id: $cartId") }

        cart.items.removeIf { it.product.id == productId }
        cart.totalSalePrice = handleTotalPrice(cart)

        cartRepository.save(cart)
    }

    override fun handleTotalPrice(cart: Cart): Double {
        return cart.items.sumOf { item ->
            val productPrice = getProductPrice(item.product.id)
            productPrice * item.quantity
        }
    }

    private fun getProductPrice(productId: Long): Double {
        return productRepository.getProductPrice(productId)
    }

}