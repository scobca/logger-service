package org.scobca.loggerservice.services.handlers

import org.scobca.loggerservice.dto.LoggerMessage
import org.scobca.loggerservice.mappers.EventsHistoryMapper
import org.scobca.loggerservice.repositories.EventsHistoryRepository
import org.scobca.loggerservice.services.interfaces.KafkaTopicHandler
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class LoggerEventsTopicHandler(
    private val repository: EventsHistoryRepository,
    private val eventsHistoryMapper: EventsHistoryMapper,
) : KafkaTopicHandler<LoggerMessage> {

    override fun handle(
        message: LoggerMessage,
    ) {
        eventsHistoryMapper.recordFromDto(message, Instant.now())
            .also(repository::save)
    }
}