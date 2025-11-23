package org.scobca.loggerservice.repositories

import org.scobca.loggerservice.entities.EventsHistory
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

/**
 * Reactive Spring Data repository for [EventsHistory] entities.
 *
 * Extends [ReactiveCrudRepository] to provide reactive CRUD operations for event history records,
 * with [Long] as the identifier type.
 *
 * @see EventsHistory
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface EventsHistoryRepository : ReactiveCrudRepository<EventsHistory, Long>