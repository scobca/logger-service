package org.scobca.loggerservice.enums

enum class EventsType(val description: String) {
    CREATION("New resource/entity is created"),
    UPDATING("Existing resource/entity is updated"),
    DELETION("Resource/entity is deleted"),
    AUTHENTICATION("User or system authentication events"),
    MAIL_SENDING("An email is sent"),
    QUEUE_PUSH("Enqueueing a message or task event"),
    ERROR("Errors or exceptions occurring in the system"),
}