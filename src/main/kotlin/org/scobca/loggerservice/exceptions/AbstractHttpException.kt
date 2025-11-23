package org.scobca.loggerservice.exceptions

/**
 * Abstract base class for HTTP-related exceptions.
 *
 * This class extends [RuntimeException] and introduces two main properties: an HTTP status code
 * and an optional error message. Subclasses should specify concrete details for specific HTTP error cases.
 *
 * @property status the HTTP status code associated with this exception.
 * @property message an optional detailed error message.
 */
abstract class AbstractHttpException(val status: Int, override val message: String?) : RuntimeException(message)