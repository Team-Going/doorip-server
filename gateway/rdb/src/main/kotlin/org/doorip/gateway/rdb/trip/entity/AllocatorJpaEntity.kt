package org.doorip.gateway.rdb.trip.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.doorip.gateway.rdb.BaseJpaEntity

@Table(name = "allocator")
@Entity
internal class AllocatorJpaEntity : BaseJpaEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocator_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    lateinit var participant: ParticipantJpaEntity
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    lateinit var todo: TodoJpaEntity
        protected set
}
