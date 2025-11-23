package org.scobca.loggerservice.services.handlers

import org.scobca.loggerservice.dto.LoggerMessage
import org.scobca.loggerservice.mappers.EventsHistoryMapper
import org.scobca.loggerservice.repositories.EventsHistoryRepository
import org.scobca.loggerservice.services.interfaces.KafkaTopicHandler
import org.springframework.stereotype.Service
import java.time.Instant

/**
 * Topic handler for logger events, responsible for saving them to the database.
 *
 * Transforms [LoggerMessage] data to [org.scobca.loggerservice.entities.EventsHistory] entities using [EventsHistoryMapper] and
 * persists them asynchronously using [EventsHistoryRepository]. Implements [KafkaTopicHandler] for [LoggerMessage].
 *
 * @property repository the Spring Data repository for persisting event histories
 * @property eventsHistoryMapper the mapper used for converting [LoggerMessage] to [org.scobca.loggerservice.entities.EventsHistory]
 * @see KafkaTopicHandler
 * @see EventsHistoryRepository
 * @see EventsHistoryMapper
 * @see LoggerMessage
 */
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