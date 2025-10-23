@file:Suppress("UNCHECKED_CAST")

package org.careerseekers.csloggerservice.io.converters

import org.careerseekers.csloggerservice.io.BasicSuccessfulResponse

interface ConvertableToHttpResponse<T : ConvertableToHttpResponse<T>> {
    fun toHttpResponse(): BasicSuccessfulResponse<T> = BasicSuccessfulResponse(this as T)
}