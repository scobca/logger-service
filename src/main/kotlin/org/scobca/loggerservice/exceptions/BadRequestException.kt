package org.scobca.loggerservice.exceptions

import org.springframework.http.HttpStatus

/**
 * Exception representing an HTTP 400 Bad Request response.
 *
 * Extends [AbstractHttpException], setting the HTTP status code to 400 (Bad Request).
 *
 * @param message a detailed description of the error
 * @see AbstractHttpException
 */
class BadRequestException(override val message: String) :
    AbstractHttpException(HttpStatus.BAD_REQUEST.value(), message)