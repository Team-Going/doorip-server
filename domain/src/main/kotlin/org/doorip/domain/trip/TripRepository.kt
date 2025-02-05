package org.doorip.domain.trip

import java.time.LocalDate
import org.doorip.domain.user.UserId

interface TripRepository {
    fun createTrip(userId: UserId, title: String, startAt: LocalDate, endAt: LocalDate, styles: PropensityTag): Trip
}
