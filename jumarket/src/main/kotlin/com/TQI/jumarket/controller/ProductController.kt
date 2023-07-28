package com.TQI.jumarket.controller

import com.TQI.jumarket.domain.Service.CategoryService
import com.TQI.jumarket.domain.data.dto.ProductDto
import com.TQI.jumarket.domain.data.model.Product
import com.TQI.jumarket.domain.Service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/product")
@Tag(name = "Product Controller", description = "RESTful API for managing the products")
class ProductController(private val productService: ProductService) {
    @GetMapping
    @Operation(
        summary = "Get all products",
        description = "Retrieve a list of all registered products. " +
                "This operation returns a complete list of all products available in the system. " +
                "Use this endpoint to fetch the entire collection of products."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findAll(): ResponseEntity<List<ProductDto>> {
        val products = productService.findAll()
        val productDtos = products.map { ProductDto(it) }

        return ResponseEntity.ok(productDtos)
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get product by id",
        description = "Retrieve a specific product by its unique ID. " +
                "The `{id}` path variable should be replaced with the ID of the product to retrieve."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findById(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val product = productService.findById(id)
        val productDto = ProductDto(product)
        return ResponseEntity.ok(productDto)
    }

    @GetMapping("/name/{productName}")
    @Operation(
        summary = "Find products by name",
        description = "Retrieve a list of all registered products that contain the searched term in their name. " +
                "The search is case-insensitive, meaning it will match products regardless of the letter case. " +
                "The `{productName}` path variable should be replaced with the term to search for."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findByProductName(@PathVariable productName: String): ResponseEntity<List<ProductDto>> {
        val products = productService.listProductByName(productName)
        val productDtos = products.map { ProductDto(it) }
        return ResponseEntity.ok(productDtos)
    }
    @GetMapping("/category/{categoryId}")
    @Operation(
        summary = "Find products by category",
        description = "Retrieve a list of all registered products that belong to the specified category. " +
                "The search is based on the category ID provided in the path variable {categoryId}. " +
                "The endpoint will return products that are associated with the given category."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findByProductCategory(@PathVariable categoryId: Long): ResponseEntity<List<ProductDto>> {
        val products = productService.listProductsByCategory(categoryId)
        val productDtos = products.map { ProductDto(it) }
        return ResponseEntity.ok(productDtos)
    }
    @PostMapping
    @Operation(
        summary = "Create a new product",
        description = "Create a new product and return the created product's data"
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "201", description = "Product created successfully"), ApiResponse(
            responseCode = "400",
            description = "Invalid product data provided"
        )]
    )
    fun create(@RequestBody productDTO: ProductDto): ResponseEntity<ProductDto> {
        val product = productService.create(productDTO.toEntity())
        val location =
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.id).toUri()

        return ResponseEntity.created(location).body(ProductDto(product))
    }

    @PatchMapping("/{id}")
    @Operation(
        summary = "Update product",
        description = "Update an existing product with the provided information. " +
                "The product will be identified by its unique ID. " +
                "Only the fields that need to be updated should be provided. " +
                "If a field is not included in the request, its value will remain unchanged."
    )
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody @Valid productDTO: ProductDto): ResponseEntity<Product>{
        val product = productDTO.toEntity()

        val updatedProduct = productService.update(id, product)

        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete product",
        description = "Delete an existing product by its unique ID. " +
                "The `{id}` path variable should be replaced with the ID of the product to be deleted."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            ApiResponse(responseCode = "404", description = "Product not found")]
    )
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<String> {
        productService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product id $id deleted successfully")
    }
}
