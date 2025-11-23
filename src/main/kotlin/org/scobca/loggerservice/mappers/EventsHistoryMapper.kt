package org.scobca.loggerservice.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.scobca.loggerservice.dto.LoggerMessage
import org.scobca.loggerservice.entities.EventsHistory
import java.time.Instant

@Mapper(componentModel = "spring")
interface EventsHistoryMapper {

    @Mapping(target = "createdAt", source = "createdAt")
    fun recordFromDto(dto: LoggerMessage, createdAt: Instant): EventsHistory
}