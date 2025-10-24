@file:Suppress("UNCHECKED_CAST")

package org.scobca.loggerservice.io.converters

import org.scobca.loggerservice.io.BasicSuccessfulResponse

interface ConvertableToHttpResponse<T : ConvertableToHttpResponse<T>> {
    fun toHttpResponse(): BasicSuccessfulResponse<T> = BasicSuccessfulResponse(this as T)
}