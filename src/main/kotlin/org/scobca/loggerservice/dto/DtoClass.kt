package org.scobca.loggerservice.dto

import org.scobca.loggerservice.io.converters.ConvertableToHttpResponse

/**
 * Marker interface for all Data Transfer Objects (DTO) in the logging service.
 *
 * Extends [ConvertableToHttpResponse] to enforce conversion from DTO to universal HTTP response format.
 */
interface DtoClass : ConvertableToHttpResponse<DtoClass>