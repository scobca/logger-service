package org.scobca.loggerservice.dto

import kotlinx.serialization.Serializable

@Serializable
data class EventDescriptionDto(
    val message: String? = null,
    val details: String? = null,
    val metadata: Map<String, String>? = null
) : DtoClass
