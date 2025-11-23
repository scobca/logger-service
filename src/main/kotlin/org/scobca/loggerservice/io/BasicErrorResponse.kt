package org.scobca.loggerservice.io

import kotlinx.serialization.Serializable

/**
 * Response structure for simple error cases in the API.
 *
 * Represents error details using a string message and an HTTP status code.
 * Used whenever an error scenario needs to be reported to the client.
 *
 * @property status the HTTP status code of the error
 * @property message the error message
 * @see AbstractResponse
 */
@Serializable
data class BasicErrorResponse(override val status: Int, override val message: String) :
    AbstractResponse<String>
