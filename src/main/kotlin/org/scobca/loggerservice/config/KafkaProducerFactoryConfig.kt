package org.scobca.loggerservice.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.scobca.loggerservice.dto.KafkaMessagesDto
import org.scobca.loggerservice.serializers.PolymorphicKafkaSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

/**
 * Kafka producer configuration for the logging service.
 *
 * This configuration class defines the Kafka [ProducerFactory] and [KafkaTemplate]
 * for sending messages of type [KafkaMessagesDto] to Kafka topics.
 *
 * It configures Kafka producer properties such as bootstrap servers,
 * serializers, retries, reliability guarantees, batching, and timeouts.
 *
 * @param T the type of messages to produce, extending [KafkaMessagesDto].
 * @property properties encapsulated Kafka cluster properties.
 */
@Configuration
class KafkaProducerFactoryConfig<T : KafkaMessagesDto>(private val properties: KafkaProperties) {

    /**
     * Creates the Kafka [ProducerFactory] bean configured with producer properties.
     *
     * @return a configured [ProducerFactory] for producing messages of type T.
     */
    @Bean
    fun producerFactory(): ProducerFactory<String, T> {
        val configProps = mapOf(

            /**
             * Kafka cluster connection settings
             * Connecting to Kafka and serializing keys and values
             */
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to properties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to PolymorphicKafkaSerializer::class.java,

            /**
             * Retries settings
             * Repeated attempts to send messages with temporary errors
             */
            ProducerConfig.RETRIES_CONFIG to Int.MAX_VALUE,
            ProducerConfig.RETRY_BACKOFF_MS_CONFIG to 500,

            /**
             * Reliability and ordering settings
             * Guarantees of delivery and order of messages
             */
            ProducerConfig.ACKS_CONFIG to "all",
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to true,
            ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION to 5,

            /**
             * Performance tuning
             * Batching and delay settings to increase throughput
             */
            ProducerConfig.LINGER_MS_CONFIG to 100,
            ProducerConfig.BATCH_SIZE_CONFIG to 32 * 1024,

            /**
             * Timeout settings
             * Timeouts for request processing and message delivery
             */
            ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to 30_000,
            ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG to 120_000
        )

        return DefaultKafkaProducerFactory(configProps)
    }

    /**
     * Creates the [KafkaTemplate] bean for sending Kafka messages.
     *
     * @return a [KafkaTemplate] for producing messages of type T.
     */
    @Bean
    fun emailSendingKafkaProducer(): KafkaTemplate<String, T> {
        return KafkaTemplate(producerFactory())
    }
}