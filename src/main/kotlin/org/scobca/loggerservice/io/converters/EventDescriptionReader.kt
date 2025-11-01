package org.scobca.loggerservice.io.converters

import kotlinx.serialization.json.Json
import org.scobca.loggerservice.dto.EventDescriptionDto
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class EventDescriptionReader : Converter<String, EventDescriptionDto> {
    private val json = Json {
        prettyPrint = false
    }

    override fun convert(source: String): EventDescriptionDto {
        return runCatching {
            json.decodeFromString<EventDescriptionDto>(source)
        }.getOrElse {
            EventDescriptionDto()
        }
    }
}