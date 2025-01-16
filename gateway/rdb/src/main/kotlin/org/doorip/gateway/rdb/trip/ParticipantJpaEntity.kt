package org.doorip.gateway.rdb.trip

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.doorip.domain.trip.PropensityTag

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
}
