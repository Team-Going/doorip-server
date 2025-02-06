package org.doorip.domain.trip

import java.time.LocalDate
import org.doorip.domain.user.UserId

interface TripRepository {
    fun createTrip(userId: UserId, title: String, startAt: LocalDate, endAt: LocalDate, styles: PropensityTag): Trip
    fun verifyTripInvitation(code: String): Trip?
    fun entryTrip(userId: UserId, tripId: TripId, styles: PropensityTag)
    fun getTrip(tripId: TripId): Trip?
    fun validateIsValidTrip(tripId: TripId)
}
