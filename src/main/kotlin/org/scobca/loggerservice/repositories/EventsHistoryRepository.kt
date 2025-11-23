package org.scobca.loggerservice.repositories

import org.scobca.loggerservice.entities.EventsHistory
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventsHistoryRepository : ReactiveCrudRepository<EventsHistory, Long>