package com.TQI.jumarket.domain.exceptions

object ErrorMessages {
    const val RECORD_NOT_FOUND : String = "The requested record was not found"
    const val DUPLICATE_RECORD : String = "A record with the same XXX already exists in the database."
    const val REGISTRATION_NOT_COMPLETED : String = "It was not possible to register"
    const val CATEGORY_ALREADY_EXISTS : String = "Already exists a category with this name"
    const val PRODUCT_ALREADY_EXISTS : String = "Already exists a product with this name"
    const val CANNOT_UPDATE_CATEGORY: String = "The category to be updated must be the same or product don't exists"
    const val CANNOT_UPDATE_PRODUCT: String = "The product to be updated must be the same or product don't exists"
    const val CANNOT_UPDATE_SALE: String = "The sale to be updated must be the same or sale don't exists"
    const val ID_ALREADY_EXISTS: String = "The ID informed already exists"
}