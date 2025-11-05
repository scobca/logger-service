package org.scobca.loggerservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.scobca.loggerservice.enums.KafkaTopics
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicsConfig {

    @Bean
    fun loggerEventsTopic(): NewTopic {
        return TopicBuilder
            .name(KafkaTopics.LOGGER_EVENTS_TOPIC.name)
            .partitions(12)
            .replicas(3)
            .build()
    }
}