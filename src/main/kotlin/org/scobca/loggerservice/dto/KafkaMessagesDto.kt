package org.scobca.loggerservice.dto

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.scobca.loggerservice.enums.EventsType
import org.scobca.loggerservice.enums.ServiceType
import org.scobca.loggerservice.serializers.UUIDSerializer
import java.util.UUID

@Serializable
@Polymorphic
sealed class KafkaMessagesDto : DtoClass

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