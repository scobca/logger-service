package org.scobca.loggerservice.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.scobca.loggerservice.dto.KafkaMessagesDto
import org.scobca.loggerservice.serializers.PolymorphicKafkaSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties

/**
 * Kafka consumer configuration for the logging service.
 *
 * This configuration class sets up a Kafka consumer factory and a concurrent
 * Kafka listener container factory for consuming messages of type [KafkaMessagesDto].
 *
 * Consumer properties include connection to Kafka cluster, deserialization,
 * consumer group configuration, manual commit mode, and fetch settings.
 *
 * The container factory is configured for manual immediate acknowledgment mode.
 *
 * @property properties Kafka connection and cluster properties encapsulated in [KafkaProperties].
 */
@EnableKafka
@Configuration
class KafkaConsumerFactoryConfig(private val properties: KafkaProperties) {

    /**
     * Creates the [ConsumerFactory] bean for Kafka consumers.
     *
     * Configures Kafka consumer settings such as bootstrap servers, consumer group,
     * deserializers, commit behavior, and fetching options.
     *
     * @return a configured [ConsumerFactory] for consuming [KafkaMessagesDto].
     */
    @Bean
    fun consumerFactory(): ConsumerFactory<String, KafkaMessagesDto> {
        val configProps = mapOf(
            /**
             *  Kafka cluster connection settings
             *  Connecting to Kafka and serializing keys and values
             */
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to properties.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "logger_service_consumers",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to PolymorphicKafkaSerializer::class.java,

            /**
             *  Auto commit configuration
             */
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,
            ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG to 1000,

            /**
             *  Session and heartbeat settings
             */
            ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG to 20000,
            ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG to 1000,

            /**
             *  Fetch settings
             */
            ConsumerConfig.FETCH_MIN_BYTES_CONFIG to 1,
            ConsumerConfig.FETCH_MAX_BYTES_CONFIG to 52428800, // 50MB
            ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG to 1048576, // 1MB

            /**
             * Offset reset policy
             */
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",

            /**
             *  Max poll records
             */
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG to 500,

            /**
             * Isolation level for transactions
             */
            ConsumerConfig.ISOLATION_LEVEL_CONFIG to "read_committed"
        )


        return DefaultKafkaConsumerFactory(configProps)
    }

    /**
     * Creates the [ConcurrentKafkaListenerContainerFactory] bean.
     *
     * This factory uses the configured consumer factory and sets the acknowledgment mode
     * to manual immediate, allowing for manual control over commit offsets.
     *
     * @param consumerFactory the Kafka consumer factory bean.
     * @return a configured concurrent Kafka listener container factory.
     */
    @Bean
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, KafkaMessagesDto>
    ): ConcurrentKafkaListenerContainerFactory<String, KafkaMessagesDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, KafkaMessagesDto>()

        factory.consumerFactory = consumerFactory
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE

        return factory
    }
}