package org.scobca.loggerservice.io.handlers

import org.scobca.loggerservice.exceptions.BadRequestException
import org.scobca.loggerservice.exceptions.NotFoundException
import org.scobca.loggerservice.io.BasicErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Global HTTP exception handler for REST controllers.
 *
 * Provides centralized handling of exceptions thrown by any controller within the application,
 * converting them into consistent [BasicErrorResponse] objects with appropriate HTTP status codes.
 * Handles both general exceptions (500 Internal Server Error) and custom project exceptions
 * such as [NotFoundException] and [BadRequestException].
 *
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 * @see NotFoundException
 * @see BadRequestException
 * @see BasicErrorResponse
 */
@RestControllerAdvice
class GlobalHttpExceptionHandler {

    /**
     * Basic exceptions handler
     * @return ResponseEntity with status 500 and exception message
     */
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<BasicErrorResponse> {
        val errorResponse = BasicErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "${ex::class}; ${ex.message}",
        )

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    /**
     * Custom project exceptions handler
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<BasicErrorResponse> {
        val errorResponse = BasicErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message
        )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException): ResponseEntity<BasicErrorResponse> {
        val errorResponse = BasicErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}