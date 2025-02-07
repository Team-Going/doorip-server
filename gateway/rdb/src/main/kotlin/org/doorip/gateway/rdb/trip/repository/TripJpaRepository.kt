package org.doorip.gateway.rdb.trip.repository

import org.doorip.gateway.rdb.trip.entity.TripJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface TripJpaRepository : JpaRepository<TripJpaEntity, Long> {
    fun existsByCode(code: String): Boolean
    fun findByCode(code: String): TripJpaEntity?
}
