package org.scobca.loggerservice.io.converters

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.scobca.loggerservice.dto.EventDescriptionDto
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

/**
 * Converter that serializes an [EventDescriptionDto] into its JSON [String] representation.
 *
 * This class uses kotlinx.Serialization to encode an [EventDescriptionDto] as a JSON string,
 * including default values in the output. It is annotated with [org.springframework.data.convert.WritingConverter],
 * making it suitable for use in Spring Data conversions to store objects as strings in the database.
 *
 * @see EventDescriptionDto
 */
@WritingConverter
class EventDescriptionWriter : Converter<EventDescriptionDto, String> {
    private val json = Json {
        encodeDefaults = true
        prettyPrint = false
    }

    override fun convert(source: EventDescriptionDto): String {
        return json.encodeToString(source)
    }
}