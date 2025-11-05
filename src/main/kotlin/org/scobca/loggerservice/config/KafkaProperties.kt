package org.scobca.loggerservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Configuration properties for Kafka connection.
 *
 * This class binds properties with prefix "spring.kafka" from the application's
 * configuration files (resources/application.yaml) to
 * strongly typed Kotlin fields.
 *
 * @property bootstrapServers Comma-separated list of Kafka bootstrap server addresses.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
class KafkaProperties {
    lateinit var bootstrapServers: String
}