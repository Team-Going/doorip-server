package org.doorip.core.trip

import java.time.LocalDate
import org.doorip.domain.UserNotFoundException
import org.doorip.domain.trip.PropensityTag
import org.doorip.domain.trip.Trip
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
    override fun createTrip(userId: UserId, title: String, startDate: LocalDate, endDate: LocalDate, styles: PropensityTag): Trip {
        userRepository.getUser(userId) ?: throw UserNotFoundException

        val trip = tripRepository.createTrip(
            userId = userId,
            title = title,
            startAt = startDate,
            endAt = endDate,
            styles = styles,
        )

        return trip
    }
}
