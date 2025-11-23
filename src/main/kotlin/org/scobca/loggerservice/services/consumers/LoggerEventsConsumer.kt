package org.scobca.loggerservice.services.consumers

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.scobca.loggerservice.dto.LoggerMessage
import org.scobca.loggerservice.services.handlers.LoggerEventsTopicHandler
import org.scobca.loggerservice.services.interfaces.CustomKafkaConsumer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

/**
 * Kafka consumer service for processing logger events.
 *
 * Listens to messages from the `"LOGGER_EVENTS_TOPIC"` topic as part of the `"logger_events_consumers_group"` group.
 * Delegates message processing to [LoggerEventsTopicHandler] and acknowledges messages after successful handling.
 * Implements [CustomKafkaConsumer] with string keys and [LoggerMessage] values.
 *
 * @property topicHandler the message handler responsible for processing each [LoggerMessage]
 * @see CustomKafkaConsumer
 * @see LoggerEventsTopicHandler
 * @see org.springframework.kafka.annotation.KafkaListener
 */
@Service
class LoggerEventsConsumer(override val topicHandler: LoggerEventsTopicHandler) : CustomKafkaConsumer<String, LoggerMessage> {

    /**
     * Receives and processes messages from Kafka.
     *
     * @param consumerRecord the incoming Kafka record
     * @param acknowledgment the Kafka acknowledgment handler for offset management
     */
    @KafkaListener(
        topics = ["LOGGER_EVENTS_TOPIC"],
        groupId = "logger_events_consumers_group",
    )
    override fun receiveMessage(
        consumerRecord: ConsumerRecord<String, LoggerMessage>,
        acknowledgment: Acknowledgment
    ) {
        topicHandler.handle(consumerRecord.value())
        acknowledgment.acknowledge()
    }
}