package org.scobca.loggerservice.enums

/**
 * Enum representing types of events that can occur within the system.
 *
 * Each event type has an associated description that provides a human-readable
 * explanation of the event.
 *
 * @property description A brief summary describing the event type.
 */
enum class EventsType(val description: String) {
    CREATION("New resource/entity is created"),
    UPDATING("Existing resource/entity is updated"),
    DELETION("Resource/entity is deleted"),
    AUTHENTICATION("User or system authentication events"),
    MAIL_SENDING("An email is sent"),
    QUEUE_PUSH("Enqueueing a message or task event"),
    ERROR("Errors or exceptions occurring in the system"),
}