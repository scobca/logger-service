package org.scobca.loggerservice.entities

import org.scobca.loggerservice.dto.EventDescriptionDto
import org.scobca.loggerservice.enums.EventsType
import org.scobca.loggerservice.enums.ServiceType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table(name = "event_history")
data class EventsHistory (
    @Id
    val id: Long? = null,
    val serviceType: ServiceType,
    val eventType: EventsType,
    val eventDescription: EventDescriptionDto? = null,
    val userId: Long? = null,
    val userUUID: UUID? = null,
    val userIpAddress: String? = null,
    val createdAt: Instant = Instant.now(),
)