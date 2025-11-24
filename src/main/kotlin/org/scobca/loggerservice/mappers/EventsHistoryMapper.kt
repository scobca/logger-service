package org.scobca.loggerservice.mappers

import kotlinx.serialization.encodeToString
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.scobca.loggerservice.dto.EventDescriptionDto
import org.scobca.loggerservice.dto.LoggerMessage
import org.scobca.loggerservice.entities.EventsHistory
import org.scobca.loggerservice.serializers.CustomSerializerModule.json
import java.time.Instant

/**
 * Mapper interface for converting between [LoggerMessage] DTOs and [EventsHistory] entities.
 *
 * Utilizes MapStruct to generate mapping code at compile-time.
 **
 * @see org.mapstruct.Mapper
 * @see org.mapstruct.Mapping
 * @see LoggerMessage
 * @see EventsHistory
 */
@Mapper(componentModel = "spring")
interface EventsHistoryMapper {

    @Mapping(target = "createdAt", source = "createdAt")
    fun recordFromDto(dto: LoggerMessage, createdAt: Instant): EventsHistory

    fun map(eventDescription: EventDescriptionDto?): String? =
        eventDescription?.let { json.encodeToString(it) }
}