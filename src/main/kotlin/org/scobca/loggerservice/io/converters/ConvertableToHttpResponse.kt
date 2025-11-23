@file:Suppress("UNCHECKED_CAST")

package org.scobca.loggerservice.io.converters

import org.scobca.loggerservice.io.BasicSuccessfulResponse

/**
 * Interface for types that can be converted to an HTTP response.
 *
 * Classes implementing this interface should be able to create a [BasicSuccessfulResponse] containing themselves
 * as the response payload. This pattern provides a convenient contract for domain objects or DTOs that need to be
 * directly converted to a standard HTTP success response.
 *
 * The type parameter [T] is recursively bounded to ensure type safety, so the implementing type matches the response payload.
 *
 * @return a [BasicSuccessfulResponse] containing the implementing object as payload
 * @see BasicSuccessfulResponse
 */
interface ConvertableToHttpResponse<T : ConvertableToHttpResponse<T>> {
    fun toHttpResponse(): BasicSuccessfulResponse<T> = BasicSuccessfulResponse(this as T)
}