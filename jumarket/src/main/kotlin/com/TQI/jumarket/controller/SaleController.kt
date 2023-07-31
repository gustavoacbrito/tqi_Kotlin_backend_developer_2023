package com.TQI.jumarket.controller

import com.TQI.jumarket.domain.Service.SaleService
import com.TQI.jumarket.domain.data.dto.SaleDto
import com.TQI.jumarket.domain.data.model.Sale
import com.TQI.jumarket.domain.data.repositories.CartRepository
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
@RequestMapping("/sale")
@Tag(name = "Sale Controller", description = "RESTful API for managing the sales")
class SaleController(private val saleService: SaleService, private val cartRepository: CartRepository) {
    @GetMapping
    @Operation(
        summary = "Get all sales",
        description = "Retrieve a list of all registered sales. This operation returns a complete list of all sales available in the system. Use this endpoint to fetch the entire collection of sales."
    )
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Operation successful")])
    fun findAll(): ResponseEntity<List<SaleDto>> {
        val sales = saleService.findAll()
        val saleDtos = sales.map { SaleDto(it) }
        return ResponseEntity.ok(saleDtos)
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get sale by id",
        description = "Retrieve a specific sale by its unique ID. The `{id}` path variable should be replaced with the ID of the sale to retrieve."
    )
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Operation successful")])
    fun findById(@PathVariable id: Long): ResponseEntity<SaleDto> {
        val sale = saleService.findById(id)
        val saleDto = SaleDto(sale)
        return ResponseEntity.ok(saleDto)
    }

    @PostMapping
    @Operation(
        summary = "Create a new sale", description = "Create a new sale and return the created sale's data"
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "201", description = "Sale created successfully"), ApiResponse(
            responseCode = "400", description = "Invalid sale data provided"
        )]
    )
    fun create(@RequestBody saleDto: SaleDto): ResponseEntity<SaleDto> {
        val sale = saleService.create(saleDto.toEntity())
        val cart = cartRepository.findById(saleDto.cartId)
        val location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sale.id).toUri()
        return ResponseEntity.created(location).body(SaleDto(sale))
    }

    @PatchMapping("/{id}")
    @Operation(
        summary = "Update sale", description = "Update an existing sale with the provided information"
    )
    fun updateSale(
        @PathVariable id: Long, @RequestBody @Valid saleDTO: SaleDto
    ): ResponseEntity<Sale> {
        val sale = saleDTO.toEntity()
        val updatedSale = saleService.update(id, sale)
        return ResponseEntity.ok(updatedSale)
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete sale", description = "Delete an existing sale by its unique ID"
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "204",
            description = "Sale deleted successfully"
        ), ApiResponse(
            responseCode = "404",
            description = "Sale not found"
        )]
    )
    fun deleteSale(@PathVariable id: Long): ResponseEntity<String> {
        saleService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sale id $id deleted successfully")
    }
}
