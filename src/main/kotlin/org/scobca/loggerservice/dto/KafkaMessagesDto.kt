package org.scobca.loggerservice.dto

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.scobca.loggerservice.enums.EventsType
import org.scobca.loggerservice.enums.ServiceType
import org.scobca.loggerservice.serializers.UUIDSerializer
import java.util.UUID

/**
 * Base sealed class for Kafka message DTOs, enabling polymorphic serialization.
 *
 * This facilitates handling different types of Kafka messages
 * with shared base type [KafkaMessagesDto].
 *
 * Used to create [org.scobca.loggerservice.serializers.PolymorphicKafkaSerializer]
 */
@Serializable
@Polymorphic
sealed class KafkaMessagesDto : DtoClass

/**
 * Represents a basic logger message sent via Kafka.
 *
 * @property serviceType The service where the event originated.
 * @property eventType The type of event.
 * @property eventDescription Optional detailed event description.
 * @property userId Optional numeric user identifier.
 * @property userUUID Optional UUID user identifier.
 * @property userIpAddress Optional IP address of the user.
 */
@Serializable
@SerialName("LoggerMessage")
data class LoggerMessage(
    val serviceType: ServiceType,
    val eventType: EventsType,
    val eventDescription: EventDescriptionDto? = null,
    val userId: Long? = null,
    @Serializable(with = UUIDSerializer::class)
    val userUUID: UUID? = null,
    val userIpAddress: String? = null,
) : KafkaMessagesDto()