package org.scobca.loggerservice.services.interfaces

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.support.Acknowledgment

/**
 * Custom interface for consuming Kafka records with flexible handlers and acknowledgment control.
 *
 * Intended for use with a [KafkaTopicHandler] to process messages of type [V] from Kafka records,
 * while the key type of the record is [K]. Provides explicit control over Kafka message acknowledgment
 * through the [receiveMessage] method.
 *
 * @param K the key type of the Kafka record
 * @param V the message value type of the Kafka record
 * @property topicHandler the handler for processing individual Kafka messages of type [V]
 * @see KafkaTopicHandler
 * @see org.apache.kafka.clients.consumer.ConsumerRecord
 * @see org.springframework.kafka.support.Acknowledgment
 */
interface CustomKafkaConsumer<K, V> {
    val topicHandler: KafkaTopicHandler<V>

    fun receiveMessage(consumerRecord: ConsumerRecord<K, V>, acknowledgment: Acknowledgment): Any
}