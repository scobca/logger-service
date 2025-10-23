package org.careerseekers.csloggerservice.serializers

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

object CustomSerializerModule {
    val customSerializerModule = SerializersModule {}

    val json = Json {
        serializersModule = customSerializerModule
        classDiscriminator = "type"
        ignoreUnknownKeys = true
    }
}