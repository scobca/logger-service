package org.careerseekers.csloggerservice.dto

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class KafkaMessagesDto : DtoClass