package org.scobca.loggerservice.services.interfaces

interface KafkaTopicHandler<T> {
    fun handle(message: T)
}