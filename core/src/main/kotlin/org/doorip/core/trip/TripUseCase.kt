package org.doorip.core.trip

import java.time.LocalDate
import org.doorip.domain.trip.PropensityTag
import org.doorip.domain.trip.Trip
import org.doorip.domain.user.UserId

interface TripUseCase {
    fun createTrip(userId: UserId, title: String, startDate: LocalDate, endDate: LocalDate, styles: PropensityTag): Trip
}
