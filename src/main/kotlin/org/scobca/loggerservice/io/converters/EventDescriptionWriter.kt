package org.scobca.loggerservice.io.converters

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.scobca.loggerservice.dto.EventDescriptionDto
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

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