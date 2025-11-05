package org.scobca.loggerservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.scobca.loggerservice.enums.KafkaTopics
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

/**
 * Configuration class for Kafka topic creation.
 *
 * Defines beans for Kafka topics used in the application.
 * This enables automatic topic creation with specific partition
 * and replication settings on Kafka broker startup if the topics
 * do not already exist.
 */
@Configuration
class KafkaTopicsConfig {

    /**
     * Defines the Kafka topic for basic logger events.
     *
     * Topic name and properties like number of partitions and replication factor
     * are specified here based on the enum [KafkaTopics].
     *
     * @return a [NewTopic] bean.
     */
    @Bean
    fun loggerEventsTopic(): NewTopic {
        return TopicBuilder
            .name(KafkaTopics.LOGGER_EVENTS_TOPIC.name)
            .partitions(12)
            .replicas(3)
            .build()
    }
}