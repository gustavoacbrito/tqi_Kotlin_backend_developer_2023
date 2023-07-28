package com.TQI.jumarket.domain.exceptions

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.OffsetDateTime

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException, request: WebRequest): ResponseEntity<Any> {
        val fields = mutableListOf<ErrorInfo.Field>()
        for (violation in ex.constraintViolations) {
            val name = violation.propertyPath.toString()
            val message = violation.message
            fields.add(ErrorInfo.Field(name, message))
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorInfo(
                title = ex.message,
                dateTime = OffsetDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                fields = fields
            )
        )
    }
    @ExceptionHandler(BusinessRulesException::class)
    fun handlerValidException(ex: BusinessRulesException): ResponseEntity<ErrorInfo> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorInfo(
                title = ex.message,
                dateTime = OffsetDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString()
            )
        )
    }
    @ExceptionHandler(EntityNotFoundException::class)
    fun handlerEntityNotFoundException(ex: EntityNotFoundException) : ResponseEntity<ErrorInfo> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorInfo(
                title = ex.message,
                dateTime = OffsetDateTime.now(),
                status = HttpStatus.NOT_FOUND.value(),
                exception = ex.javaClass.toString()
            )
        )
    }
    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerIllegalArgumentException(ex: IllegalArgumentException) : ResponseEntity<ErrorInfo> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorInfo(
                title = ex.message,
                dateTime = OffsetDateTime.now(),
                status = HttpStatus.NOT_FOUND.value(),
                exception = ex.javaClass.toString()
            )
        )
    }
}