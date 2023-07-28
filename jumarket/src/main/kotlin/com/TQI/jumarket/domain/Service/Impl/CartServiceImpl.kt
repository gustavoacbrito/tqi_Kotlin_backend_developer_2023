package com.TQI.jumarket.domain.Service.Impl

import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.repositories.CartRepository
import com.TQI.jumarket.domain.Service.CartService
import com.TQI.jumarket.domain.data.model.CartItem
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
        dbCart.totalSalePrice = model.totalSalePrice

        return cartRepository.save(dbCart)
    }

    @Transactional
    override fun delete(id: Long) {
        val dbCart = this.findById(id)
        return cartRepository.delete(dbCart)
    }

    override fun addItemToCart(cartId: Long,cartItem: CartItem): Cart {
        val cart = cartRepository.findById(cartId).orElseThrow { NoSuchElementException("Cart not found with id: $cartId") }
        val existingItem = cart.items.find { it.product.id == cartItem.product.id }
        if (existingItem != null){
            existingItem.quantity += cartItem.quantity
        } else {
            cart.items.add(cartItem)
        }
        cartItem.totalItemsCost = cartItem.quantity * getProductPrice(cartItem.product.id)
        cart.totalSalePrice = handleTotalPrice(cart)
        cartItem.cart?.id = cartId
        cartItemRepository.save(cartItem)

        return this.update(cartId,cart)

}

    override fun removeItem(cartId: Long, productId: Long) : Cart{
        val cart = cartRepository.findById(cartId).orElseThrow { NoSuchElementException("Cart not found with id: $cartId") }

        cart.items.removeIf { it.product.id == productId }
        cart.totalSalePrice = handleTotalPrice(cart)
        val cartItem = cartItemRepository.findByProductId(productId)
        for (item in cartItem){
            cartItemRepository.delete(item)
        }
        return cartRepository.save(cart)

    }

    override fun handleTotalPrice(cart: Cart): Double {
        return cart.items.sumOf { item ->
            val productPrice = getProductPrice(item.product.id)
            productPrice * item.quantity
        }
    }

    private fun getProductPrice(productId: Long): Double {
        val price = productRepository.findPriceById(productId)
        return price
    }

}