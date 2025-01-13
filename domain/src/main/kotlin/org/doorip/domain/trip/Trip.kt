package org.doorip.domain.trip

import java.time.LocalDateTime

@JvmInline
value class TripId(
    val value: Long,
)

data class Trip(
    val id: TripId,
    val code: String,
    val title: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val participants: List<Participant>,
)
