package org.doorip.gateway.rdb.trip

import java.time.LocalDate
import org.doorip.domain.UserNotFoundException
import org.doorip.domain.trip.PropensityTag
import org.doorip.domain.trip.Trip
import org.doorip.domain.trip.TripRepository
import org.doorip.domain.user.UserId
import org.doorip.gateway.rdb.user.UserJpaRepository
import org.doorip.support.utils.RandomCodeGenerator
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
internal class TripGateway(
    private val userJpaRepository: UserJpaRepository,
    private val tripJpaRepository: TripJpaRepository,
) : TripRepository {

    @Transactional
    override fun createTrip(userId: UserId, title: String, startAt: LocalDate, endAt: LocalDate, styles: PropensityTag): Trip {
        val user = userJpaRepository.findByIdOrNull(userId.value) ?: throw UserNotFoundException

        val trip = TripJpaEntity.of(
            code = generateCode(),
            title = title,
            startDate = startAt,
            endDate = endAt,
        )

        ParticipantJpaEntity.of(
            propensityTag = styles.toEmbeddable(),
            user = user,
            trip = trip,
        )

        val savedTrip = tripJpaRepository.save(trip)

        return savedTrip.toDomain()
    }

    private fun generateCode(): String {
        var code = RandomCodeGenerator.generateRandomCode()

        while (tripJpaRepository.existsByCode(code)) {
            code = RandomCodeGenerator.generateRandomCode()
        }

        return code
    }
}
