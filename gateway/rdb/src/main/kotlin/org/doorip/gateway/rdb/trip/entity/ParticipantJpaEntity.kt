package org.doorip.gateway.rdb.trip.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.doorip.domain.trip.Participant
import org.doorip.domain.trip.ParticipantId
import org.doorip.gateway.rdb.BaseJpaEntity
import org.doorip.gateway.rdb.user.entity.UserJpaEntity
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

@Table(name = "participant")
@Entity
internal class ParticipantJpaEntity : BaseJpaEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set

    @Embedded
    lateinit var propensityTag: PropensityTagEmbeddable
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    lateinit var user: UserJpaEntity
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    lateinit var trip: TripJpaEntity
        protected set

    @Cascade(CascadeType.REMOVE)
    @OneToMany(mappedBy = "participant")
    val allocators: MutableList<AllocatorJpaEntity> = mutableListOf()

    internal fun changeTrip(trip: TripJpaEntity) {
        if (::trip.isInitialized) {
            this.trip.removeParticipant(this)
        }
        this.trip = trip
        trip.addParticipant(this)
    }

    companion object {
        fun of(propensityTag: PropensityTagEmbeddable, user: UserJpaEntity, trip: TripJpaEntity): ParticipantJpaEntity {
            val participant = ParticipantJpaEntity().apply {
                this.propensityTag = propensityTag
                this.user = user
            }
            participant.changeTrip(trip)

            return participant
        }
    }
}

internal fun ParticipantJpaEntity.toDomain(): Participant {
    return Participant(
        id = ParticipantId(id),
        propensityTag = propensityTag.toDomain(),
    )
}
