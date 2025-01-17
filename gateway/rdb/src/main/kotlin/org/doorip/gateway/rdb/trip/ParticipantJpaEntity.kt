package org.doorip.gateway.rdb.trip

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
import org.doorip.domain.trip.PropensityTag
import org.doorip.gateway.rdb.user.UserJpaEntity
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

@Table(name = "participant")
@Entity
internal class ParticipantJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set

    @Embedded
    lateinit var propensityTag: PropensityTag
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
}
