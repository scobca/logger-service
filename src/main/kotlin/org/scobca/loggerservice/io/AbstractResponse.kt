package org.scobca.loggerservice.io

/**
 * Generic contract for HTTP responses within the application.
 *
 * Represents the basic structure shared by both successful and error HTTP responses.
 * Implementing classes define the response's HTTP status code and a message or payload.
 *
 * @param T the type of the message or payload carried by the response
 * @property status the HTTP status code of the response
 * @property message the payload or error message; may be null
 */
interface AbstractResponse<T> {
    val status: Int
    val message: T?
}