package com.TQI.jumarket.controller

import com.TQI.jumarket.domain.data.dto.CategoryDto
import com.TQI.jumarket.domain.Service.CategoryService
import com.TQI.jumarket.domain.data.model.Category
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
@RequestMapping("/category")
@Tag(name = "Category Controller", description = "RESTful API for managing the categories")
class CategoryController(private val categoryService: CategoryService) {
    @GetMapping
    @Operation(
        summary = "Get all categories",
        description = "Retrieve a list of all registered categories. " +
                "This operation returns a complete list of all categories available in the system. " +
                "Use this endpoint to fetch the entire collection of categories."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findAll(): ResponseEntity<List<CategoryDto>> {
        val category = categoryService.findAll()
        val categoryDto = category.map { CategoryDto(it) }

        return ResponseEntity.ok(categoryDto)
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get category by id",
        description = "Retrieve a specific category by its unique ID. " +
                "The `{id}` path variable should be replaced with the ID of the category to retrieve."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findById(@PathVariable id: Long): ResponseEntity<CategoryDto> {
        val category = categoryService.findById(id)
        val categoryDto = CategoryDto(category)
        return ResponseEntity.ok(categoryDto)
    }

    @GetMapping("/name/{categoryName}")
    @Operation(
        summary = "Find categories by name",
        description = "Retrieve a list of all registered categories that contain the searched term in their name. " +
                "The search is case-insensitive, meaning it will match categories regardless of the letter case. " +
                "The `{categoryName}` path variable should be replaced with the term to search for."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Operation successful")]
    )
    fun findByCategoryName(@PathVariable categoryName: String): ResponseEntity<List<CategoryDto>> {
        val category = categoryService.listCategoryByName(categoryName)
        val categoryDto = category.map { CategoryDto(it) }
        return ResponseEntity.ok(categoryDto)
    }

    @PostMapping
    @Operation(
        summary = "Create a new category",
        description = "Create a new category and return the created category's data"
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "201", description = "Category created successfully"), ApiResponse(
            responseCode = "400",
            description = "Invalid category data provided"
        )]
    )
    fun create(@RequestBody categoryDTO: CategoryDto): ResponseEntity<CategoryDto> {
        val category = categoryService.create(categoryDTO.toEntity())
        val location =
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.id).toUri()

        return ResponseEntity.created(location).body(CategoryDto(category))
    }
    @PatchMapping("/{id}")
    @Operation(
        summary = "Update category",
        description = "Update an existing category with the provided information. " +
                "The category will be identified by its unique ID. " +
                "Only the fields that need to be updated should be provided. " +
                "If a field is not included in the request, its value will remain unchanged."
    )
    fun updateCategory(
        @PathVariable id: Long,
        @RequestBody @Valid categoryDTO: CategoryDto): ResponseEntity<Category>{
        val category = categoryDTO.toEntity()

        val updatedCategory = categoryService.update(id,category)

        return ResponseEntity.ok(updatedCategory)
    }
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete category",
        description = "Delete an existing category by its unique ID. " +
                "The `{id}` path variable should be replaced with the ID of the category to be deleted."
    )
    @ApiResponses(
        value = [ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            ApiResponse(responseCode = "404", description = "Category not found")]
    )
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<String> {
        val deleteCategory = categoryService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category id $id deleted successfully")
    }
}