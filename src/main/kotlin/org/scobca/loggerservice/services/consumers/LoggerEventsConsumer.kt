package org.scobca.loggerservice.services.consumers

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.scobca.loggerservice.dto.LoggerMessage
import org.scobca.loggerservice.services.handlers.LoggerEventsTopicHandler
import org.scobca.loggerservice.services.interfaces.CustomKafkaConsumer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class LoggerEventsConsumer(override val topicHandler: LoggerEventsTopicHandler) : CustomKafkaConsumer<String, LoggerMessage> {

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