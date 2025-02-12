package org.doorip.core.trip

import java.time.LocalDate
import org.doorip.domain.trip.Progress
import org.doorip.domain.trip.PropensityTag
import org.doorip.domain.trip.Trip
import org.doorip.domain.trip.TripId
import org.doorip.domain.user.UserId

interface TripUseCase {
    fun createTrip(userId: UserId, title: String, startAt: LocalDate, endAt: LocalDate, styles: PropensityTag): Trip
    fun verifyInvitationCode(code: String): Trip
    fun entryTrip(userId: UserId, tripId: TripId, styles: PropensityTag): TripId
    fun getTrips(userId: UserId, progress: Progress): List<Trip>
}
