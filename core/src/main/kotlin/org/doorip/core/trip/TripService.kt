package org.doorip.core.trip

import java.time.LocalDate
import org.doorip.domain.TripNotFoundException
import org.doorip.domain.UserNotFoundException
import org.doorip.domain.trip.PropensityTag
import org.doorip.domain.trip.Trip
import org.doorip.domain.trip.TripId
import org.doorip.domain.trip.TripRepository
import org.doorip.domain.user.UserId
import org.doorip.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class TripService(
    private val userRepository: UserRepository,
    private val tripRepository: TripRepository,
) : TripUseCase {

    @Transactional
    override fun createTrip(userId: UserId, title: String, startAt: LocalDate, endAt: LocalDate, styles: PropensityTag): Trip {
        userRepository.getUser(userId) ?: throw UserNotFoundException

        val trip = tripRepository.createTrip(
            userId = userId,
            title = title,
            startAt = startAt,
            endAt = endAt,
            styles = styles,
        )

        return trip
    }

    override fun verifyInvitationCode(code: String): Trip {
        return tripRepository.verifyTripInvitation(code) ?: throw TripNotFoundException
    }

    @Transactional
    override fun entryTrip(userId: UserId, tripId: TripId, styles: PropensityTag): TripId {
        userRepository.getUser(userId) ?: throw UserNotFoundException

        tripRepository.validateIsValidTrip(tripId)

        tripRepository.entryTrip(
            userId = userId,
            tripId = tripId,
            styles = styles,
        )

        return tripId
    }
}
