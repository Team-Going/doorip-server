package org.doorip.gateway.rdb.trip

import org.springframework.data.jpa.repository.JpaRepository

internal interface TripJpaRepository : JpaRepository<TripJpaEntity, Long> {
    fun existsByCode(code: String): Boolean
}
