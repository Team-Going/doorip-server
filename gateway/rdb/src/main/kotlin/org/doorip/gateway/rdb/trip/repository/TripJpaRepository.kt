package org.doorip.gateway.rdb.trip.repository

import java.time.LocalDate
import org.doorip.gateway.rdb.trip.entity.TripJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

internal interface TripJpaRepository : JpaRepository<TripJpaEntity, Long> {
    fun existsByCode(code: String): Boolean
    fun findByCode(code: String): TripJpaEntity?

    @Query(
        "select t " +
            "from TripJpaEntity t " +
            "join ParticipantJpaEntity p " +
            "on p.trip = t " +
            "join UserJpaEntity u " +
            "on p.user = u " +
            "where u.id = :userId " +
            "and datediff(t.endDate, :now) >= 0 " +
            "order by datediff(t.startDate, :now), t.createdDate",
    )
    fun findIncompleteTrips(@Param("userId") userId: Long, @Param("now") now: LocalDate): List<TripJpaEntity>

    @Query(
        "select t " +
            "from TripJpaEntity t " +
            "join ParticipantJpaEntity p " +
            "on p.trip = t " +
            "join UserJpaEntity u " +
            "on p.user = u " +
            "where u.id = :userId " +
            "and datediff(t.endDate, :now) < 0 " +
            "order by datediff(t.startDate, :now), t.createdDate",
    )
    fun findCompleteTrips(@Param("userId") userId: Long, @Param("now") now: LocalDate): List<TripJpaEntity>
}
