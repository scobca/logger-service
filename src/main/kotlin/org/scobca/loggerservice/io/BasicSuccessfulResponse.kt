package org.scobca.loggerservice.io

import kotlinx.serialization.Serializable
import org.springframework.http.HttpStatus

/**
 * Generic response structure for successful API calls.
 *
 * Wraps a payload and the success HTTP status code (defaults to 200 OK).
 * Used to signal successful completion of a request and return data to the client.
 *
 * @param T the payload type returned on success
 * @property message the successful result or payload
 * @property status the HTTP status code of the response (defaults to 200)
 * @see AbstractResponse
 */
@Serializable
data class BasicSuccessfulResponse<T>(
    override val message: T,
    override val status: Int = HttpStatus.OK.value(),
) : AbstractResponse<T>