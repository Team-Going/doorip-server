package org.doorip.gateway.rdb.trip.repository

import org.doorip.gateway.rdb.trip.entity.ParticipantJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ParticipantJpaRepository : JpaRepository<ParticipantJpaEntity, Long> {
    fun existsByUserIdAndTripId(userId: Long, tripId: Long): Boolean
}
