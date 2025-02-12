package org.doorip.domain.trip

import java.time.LocalDate

@JvmInline
value class TripId(
    val value: Long,
)

data class Trip(
    val id: TripId,
    val code: String,
    val title: String,
    val startAt: LocalDate,
    val endAt: LocalDate,
)
