package com.jumarket.jumarketapi

import com.TQI.jumarket.domain.Service.Impl.CategoryServiceImpl
import com.TQI.jumarket.domain.data.model.Category
import com.TQI.jumarket.domain.data.repositories.CategoryRepository
import com.TQI.jumarket.domain.exceptions.BusinessRulesException
import com.TQI.jumarket.domain.exceptions.EntityNotFoundException
import com.TQI.jumarket.domain.exceptions.ErrorMessages
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*
import org.junit.jupiter.api.assertThrows


class CategoryServiceImplTest {

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @InjectMocks
    private lateinit var categoryService: CategoryServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testListCategoryByName() {

        val categoryName = "Books"
        val categories = listOf(
            Category(1L, "Books"),
            Category(2L, "Notebooks")
        )

        `when`(categoryRepository.findByCategoryNameContainingIgnoreCase(categoryName)).thenReturn(categories)


        val result = categoryService.listCategoryByName(categoryName)


        assertEquals(2, result.size)
        assertEquals("Books", result[0].categoryName)

    }

    @Test
    fun testFindAll() {

        val categories = listOf(
            Category(1L, "Books"),
            Category(2L, "Notebooks")
        )

        `when`(categoryRepository.findAll()).thenReturn(categories)


        val result = categoryService.findAll()


        assertEquals(2, result.size)

    }

    @Test
    fun testFindById() {

        val categoryId = 1L
        val category = Category(categoryId, "Books")

        `when`(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category))


        val result = categoryService.findById(categoryId)


        assertEquals(categoryId, result.id)
        assertEquals("Books", result.categoryName)

    }

    @Test
    fun testCreate() {

        val category = Category(3L, "Books")

        `when`(categoryRepository.existsCategoryByCategoryName("Books")).thenReturn(false)
        `when`(categoryRepository.existsById(3L)).thenReturn(false)
        `when`(categoryRepository.save(category)).thenReturn(category)


        val result = categoryService.create(category)


        assertEquals(3L, result.id)
        assertEquals("Books", result.categoryName)

    }

    @Test
    fun testCreateCategoryAlreadyExists() {

        val category = Category(1L, "Books")

        `when`(categoryRepository.existsCategoryByCategoryName("Books")).thenReturn(true)


        val exception = assertThrows<BusinessRulesException> { categoryService.create(category) }

        // Assertions
        assertEquals(ErrorMessages.CATEGORY_ALREADY_EXISTS, exception.message)
    }

    @Test
    fun testUpdate() {

        val categoryId = 2L
        val updatedCategory = Category(categoryId, "Electronics")

        `when`(categoryRepository.findById(categoryId)).thenReturn(Optional.of(Category(categoryId, "Notebooks")))
        `when`(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory)


        val result = categoryService.update(categoryId, updatedCategory)


        assertEquals(categoryId, result.id)
        assertEquals("Electronics", result.categoryName)

    }

    @Test
    fun testUpdateCategoryNotFound() {

        val categoryId = 3L
        val updatedCategory = Category(categoryId, "Toys")

        `when`(categoryRepository.findById(categoryId)).thenReturn(Optional.empty())


        val exception = assertThrows<IllegalArgumentException> { categoryService.update(categoryId, updatedCategory) }


        assertTrue(exception.message!!.contains(ErrorMessages.CANNOT_UPDATE_CATEGORY))
    }

    @Test
    fun testDelete() {

        val categoryId = 1L

        `when`(categoryRepository.findById(categoryId)).thenReturn(Optional.of(Category(categoryId, "Books")))


        assertDoesNotThrow { categoryService.delete(categoryId) }
    }

    @Test
    fun testDeleteCategoryNotFound() {

        val categoryId = 3L

        `when`(categoryRepository.findById(categoryId)).thenReturn(Optional.empty())


        val exception = assertThrows<EntityNotFoundException> { categoryService.delete(categoryId) }


        assertEquals(ErrorMessages.RECORD_NOT_FOUND, exception.message)
    }
}
