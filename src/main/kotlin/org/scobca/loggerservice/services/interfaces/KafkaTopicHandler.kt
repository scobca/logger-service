package org.scobca.loggerservice.services.interfaces

/**
 * Handler interface for processing messages from a Kafka topic.
 *
 * Implementations should define how to handle individual messages of type [T] received from Kafka.
 *
 * @param T the type of message to handle
 */
interface KafkaTopicHandler<T> {
    fun handle(message: T)
}