package org.scobca.loggerservice.exceptions

import org.springframework.http.HttpStatus

/**
 * Exception representing an HTTP 404 Not Found response.
 *
 * This exception extends [AbstractHttpException] and sets the HTTP status code to 404 (Not Found).
 *
 * @param message a detailed message explaining what resource was not found
 * @see AbstractHttpException
 */
class NotFoundException(override val message: String) : AbstractHttpException(HttpStatus.NOT_FOUND.value(), message)