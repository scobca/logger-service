package org.scobca.loggerservice.io.converters

import kotlinx.serialization.json.Json
import org.scobca.loggerservice.dto.EventDescriptionDto
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

/**
 * Converter that transforms a JSON [String] into an [EventDescriptionDto].
 *
 * This class uses kotlinx.Serialization to decode a JSON string into an instance of [EventDescriptionDto].
 * If the conversion fails (for example, due to malformed JSON), it returns a default-constructed [EventDescriptionDto].
 * Annotated with [org.springframework.data.convert.ReadingConverter], making it suitable for use in Spring Data conversions from database values.
 *
 * @see EventDescriptionDto
 */
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