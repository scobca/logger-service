package org.scobca.loggerservice.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

/**
 * Configuration class for Kotlinx Serialization setup.
 *
 * Defines a [KotlinSerializationJsonHttpMessageConverter] bean customized with
 * JSON serialization settings such as ignoring unknown keys in JSON input,
 * encoding default values, and pretty printing the JSON output.
 *
 * This converter is used by Spring Web to serialize and deserialize JSON payloads
 * in HTTP requests and responses using Kotlinx Serialization.
 */
@Configuration
class KotlinxSerializationConfig {

    /**
     * Creates a [KotlinSerializationJsonHttpMessageConverter] bean with custom JSON configuration.
     *
     * Configuration includes:
     * - ignoreUnknownKeys = true: ignore unknown JSON properties during deserialization
     * - encodeDefaults = true: include default values when serializing
     * - prettyPrint = true: format JSON output nicely
     *
     * @return a configured [KotlinSerializationJsonHttpMessageConverter] instance.
     */
    @Bean
    fun kotlinSerializationJsonHttpMessageConverter(): KotlinSerializationJsonHttpMessageConverter {
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            prettyPrint = true
        }
        return KotlinSerializationJsonHttpMessageConverter(json)
    }
}