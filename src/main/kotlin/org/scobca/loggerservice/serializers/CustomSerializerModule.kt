package org.scobca.loggerservice.serializers

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.scobca.loggerservice.dto.KafkaMessagesDto
import org.scobca.loggerservice.dto.LoggerMessage

/**
 * Object containing a custom serialization module and configured [Json] instance for polymorphic serialization.
 *
 * Defines an empty [SerializersModule], which can be extended later to register custom serializers.
 * The [json] instance is configured for polymorphic (de)serialization support, with:
 * - a custom class discriminator set to `"type"` for polymorphic serialization,
 * - the ability to ignore unknown JSON keys during deserialization.
 *
 * @see kotlinx.serialization.modules.SerializersModule
 * @see kotlinx.serialization.json.Json
 */
object CustomSerializerModule {
    val customSerializerModule = SerializersModule {
        polymorphic(KafkaMessagesDto::class) {
            subclass(LoggerMessage::class, LoggerMessage.serializer())
        }
    }

    val json = Json {
        serializersModule = customSerializerModule
        classDiscriminator = "type"
        ignoreUnknownKeys = true
    }
}