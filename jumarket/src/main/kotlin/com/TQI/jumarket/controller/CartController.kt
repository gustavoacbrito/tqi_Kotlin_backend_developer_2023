package com.TQI.jumarket.controller

import com.TQI.jumarket.domain.Service.CartService
import com.TQI.jumarket.domain.data.dto.CartDto
import com.TQI.jumarket.domain.data.dto.CartItemDto
import com.TQI.jumarket.domain.data.model.Cart
import com.TQI.jumarket.domain.data.model.CartItem
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart Controller", description = "RESTful API for managing the cart")
class CartController(private val cartService: CartService){

    @GetMapping
    @Operation(summary = "Get all carts", description = "Retrieve a list of all registered carts")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation sucessful")
    ])
    fun findAll(): ResponseEntity<List<CartDto>> {
        val cart = cartService.findAll()
        val cartDto = cart.map { CartDto(it) }

        return ResponseEntity.status(HttpStatus.OK).body(cartDto)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart by ID", description = "Retrieve a specific cart based on its ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation sucessful"),
        ApiResponse(responseCode = "400", description = "Cart not found")
    ])
    fun findById(@PathVariable id:Long): ResponseEntity<CartDto> {
        val cart = cartService.findById(id)
        return ResponseEntity.ok(CartDto(cart))
    }

    @PostMapping
    @Operation(summary = "Create a new cart", description = "Create a new cart and return the created cart's data")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Cart created sucessfully"),
        ApiResponse(responseCode = "422", description = "Invalid cart data provided")
    ])
    fun create (@RequestBody cartDTO: CartDto): ResponseEntity<CartDto> {
        val cart = cartService.create(cartDTO.toEntity())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(cart.id)
            .toUri()

        return ResponseEntity.created(location).body(CartDto(cart))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new cart", description = "Update the data of an existing cart based on its ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Cart updated sucessfully"),
        ApiResponse(responseCode = "404", description = "Cart not found"),
        ApiResponse(responseCode = "422", description = "Invalid cart data provided")
    ])
    fun update(@PathVariable id: Long, @RequestBody cartDTO: CartDto): ResponseEntity<CartDto> {
        val cart = cartService.update(id, cartDTO.toEntity())
        return ResponseEntity.ok(CartDto(cart))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart", description = "Delete an existing cart based on its ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Cart deleted successfully"),
        ApiResponse(responseCode = "404", description = "Cart not found")
    ])
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        cartService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{cartId}/add")
    @Operation(summary = "Add to a cart", description = "Add a new product to the cart")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Product added to the cart"),
        ApiResponse(responseCode = "404", description = "Product not added")
    ])
    fun addItemToCart(@PathVariable cartId: Long, @RequestBody cartItemDto: CartItemDto): ResponseEntity<Cart> {
        val cartItem = cartItemDto.toEntity()
        return ResponseEntity.ok(cartService.addItemToCart(cartId,cartItem))

    }

    @PostMapping("/{cartId}/product/{productId}")
    @Operation(summary = "Remove to a cart", description = "Remove a product to the cart")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product removed to the cart"),
        ApiResponse(responseCode = "404", description = "Product not added")
    ])
    fun removeItem(@PathVariable cartId: Long, @PathVariable productId: Long): ResponseEntity<CartDto> {
        val cart = cartService.removeItem(cartId,productId)
        return ResponseEntity.ok(CartDto(cart))
    }
}