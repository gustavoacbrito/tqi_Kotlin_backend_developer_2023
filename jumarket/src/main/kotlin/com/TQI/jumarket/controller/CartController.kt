package com.TQI.jumarket.controller

import com.TQI.jumarket.domain.Service.CartService
import com.TQI.jumarket.domain.data.dto.CartDto
import com.TQI.jumarket.domain.data.dto.CartItemDto
import com.TQI.jumarket.domain.data.dto.response.CartViewDto
import com.TQI.jumarket.domain.data.model.Cart
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
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
    fun findAll(): ResponseEntity<List<CartViewDto>> {
        val cart = cartService.findAll()
        val cartDto = cart.map { CartViewDto(it) }

        return ResponseEntity.status(HttpStatus.OK).body(cartDto)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart by ID", description = "Retrieve a specific cart based on its ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation sucessful"),
        ApiResponse(responseCode = "400", description = "Cart not found")
    ])
    fun findById(@PathVariable id:Long): ResponseEntity<CartViewDto> {
        val cart = cartService.findById(id)
        return ResponseEntity.ok(CartViewDto(cart))
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
    fun update(@PathVariable id: Long, @RequestBody cartDTO: CartDto): ResponseEntity<CartViewDto> {
        val cart = cartService.update(id, cartDTO.toEntity())
        return ResponseEntity.ok(CartViewDto(cart))
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
    fun addItemToCart(@RequestBody cartItemDto: CartItemDto): ResponseEntity<CartViewDto> {
        val cartItem = cartItemDto.toEntity()
        return ResponseEntity.ok(CartViewDto(cartService.addItemToCart(cartItem)))

    }

    @PatchMapping("/product/remove")
    @Operation(summary = "Remove from cart", description = "Remove a product from the cart")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product removed to the cart"),
        ApiResponse(responseCode = "404", description = "Product not added"),
        ApiResponse(responseCode = "400", description = "Bad Request")
    ])
    fun removeItem(@RequestBody cartItemDto: CartItemDto): ResponseEntity<CartViewDto> {
        val cart = cartService.removeItem(cartItemDto.toEntity())
        return ResponseEntity.ok(CartViewDto(cart))
    }
}