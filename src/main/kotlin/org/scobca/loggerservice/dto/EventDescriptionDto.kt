package org.scobca.loggerservice.dto

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing detailed description of an event.
 *
 * Contains optional fields for a textual message, additional details, and
 * any related metadata as key-value pairs.
 *
 * @property message A short human-readable message describing the event.
 * @property details Additional detailed information about the event.
 * @property metadata Optional map of key-value pairs providing extra context or metadata.
 */
@Serializable
data class EventDescriptionDto(
    val message: String? = null,
    val details: String? = null,
    val metadata: Map<String, String>? = null
) : DtoClass
