package org.scobca.loggerservice.serializers

import kotlinx.serialization.KSerializer
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import org.scobca.loggerservice.dto.KafkaMessagesDto
import org.springframework.stereotype.Component

/**
 * Kafka serializer and deserializer for polymorphic message types using kotlinx.serialization.
 *
 * Generic over [Base], which must inherit from [KafkaMessagesDto]. Serializes and deserializes messages
 * polymorphically using a custom [kotlinx.serialization.json.Json] configuration, enabling support for concrete subtypes.
 *
 * - Uses [CustomSerializerModule.json] for polymorphic serialization and deserialization.
 * - Employs the base class serializer, cast for generic usage to support all inheritors of [KafkaMessagesDto].
 *
 * Implements both [Serializer] and [Deserializer] interfaces for use with Kafka producers/consumers.
 * Ignores configuration and close methods for stateless operation.
 *
 * @param Base the base type for supported messages (extends [KafkaMessagesDto])
 * @see org.apache.kafka.common.serialization.Serializer
 * @see org.apache.kafka.common.serialization.Deserializer
 * @see KafkaMessagesDto
 * @see kotlinx.serialization.KSerializer
 */
@Suppress("UNCHECKED_CAST")
@Component
class PolymorphicKafkaSerializer<Base : KafkaMessagesDto> : Serializer<Base>, Deserializer<Base> {

    private val json = CustomSerializerModule.json
    private val baseSerializer = KafkaMessagesDto.serializer() as KSerializer<Base>

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun close() {}

    override fun deserialize(topic: String, data: ByteArray?): Base? {
        if (data == null || data.isEmpty()) return null
        return json.decodeFromString(baseSerializer, data.toString(Charsets.UTF_8))
    }

    override fun serialize(topic: String, data: Base?): ByteArray? {
        if (data == null) return null
        return json.encodeToString(baseSerializer, data).toByteArray(Charsets.UTF_8)
    }
}
