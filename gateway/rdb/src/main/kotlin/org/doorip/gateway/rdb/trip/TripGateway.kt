package org.doorip.gateway.rdb.trip

import java.time.LocalDate
import org.doorip.domain.AlreadyExistingParticipantException
import org.doorip.domain.ExceedMaximumParticipantException
import org.doorip.domain.TripNotFoundException
import org.doorip.domain.UserNotFoundException
import org.doorip.domain.trip.PropensityTag
import org.doorip.domain.trip.Trip
import org.doorip.domain.trip.TripId
import org.doorip.domain.trip.TripRepository
import org.doorip.domain.user.UserId
import org.doorip.gateway.rdb.trip.entity.ParticipantJpaEntity
import org.doorip.gateway.rdb.trip.entity.TripJpaEntity
import org.doorip.gateway.rdb.trip.entity.toDomain
import org.doorip.gateway.rdb.trip.entity.toEmbeddable
import org.doorip.gateway.rdb.trip.repository.ParticipantJpaRepository
import org.doorip.gateway.rdb.trip.repository.TripJpaRepository
import org.doorip.gateway.rdb.user.repository.UserJpaRepository
import org.doorip.support.utils.RandomCodeGenerator
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Component
internal class TripGateway(
    private val userJpaRepository: UserJpaRepository,
    private val tripJpaRepository: TripJpaRepository,
    private val participantJpaRepository: ParticipantJpaRepository,
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

    override fun verifyTripInvitation(code: String): Trip? {
        val trip = tripJpaRepository.findByCode(code) ?: return null

        return trip.toDomain()
    }

    @Transactional
    override fun entryTrip(userId: UserId, tripId: TripId, styles: PropensityTag) {
        val user = userJpaRepository.findByIdOrNull(userId.value) ?: throw UserNotFoundException

        val trip = tripJpaRepository.findByIdOrNull(tripId.value) ?: throw TripNotFoundException

        if (participantJpaRepository.existsByUserIdAndTripId(userId.value, tripId.value)) {
            throw AlreadyExistingParticipantException
        }

        val participant = ParticipantJpaEntity.of(
            propensityTag = styles.toEmbeddable(),
            user = user,
            trip = trip,
        )

        participantJpaRepository.save(participant)
    }

    override fun getTrip(tripId: TripId): Trip? {
        val trip = tripJpaRepository.findByIdOrNull(tripId.value) ?: return null

        return trip.toDomain()
    }

    override fun validateIsValidTrip(tripId: TripId) {
        val trip = tripJpaRepository.findByIdOrNull(tripId.value) ?: throw TripNotFoundException

        val participants = trip.participants

        if (participants.size >= MAXIMUM_PARTICIPANT) {
            throw ExceedMaximumParticipantException
        }
    }

    private fun generateCode(): String {
        var code = RandomCodeGenerator.generateRandomCode()

        while (tripJpaRepository.existsByCode(code)) {
            code = RandomCodeGenerator.generateRandomCode()
        }

        return code
    }

    companion object {
        private const val MAXIMUM_PARTICIPANT = 6
    }
}
