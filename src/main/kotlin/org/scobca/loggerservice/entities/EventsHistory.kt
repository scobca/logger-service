package org.scobca.loggerservice.entities

import org.scobca.loggerservice.dto.EventDescriptionDto
import org.scobca.loggerservice.enums.EventsType
import org.scobca.loggerservice.enums.ServiceType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

/**
 * Entity representing an entry in the event history log.
 *
 * This class is mapped to the "event_history" table and stores details
 * about events originating from different services in the system.
 *
 * @property id Database identifier of the event record, nullable for new entries, autoincrement via database.
 * @property serviceType The type of service where the event originated.
 * @property eventType The type of the event that occurred.
 * @property eventDescription Optional detailed description of the event.
 * @property userId Optional numeric identifier of the user related to the event.
 * @property userUUID Optional UUID identifier of the user related to the event.
 * @property userIpAddress Optional IP address of the user related to the event.
 * @property createdAt Timestamp when the event was recorded, defaults to current instant.
 */
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