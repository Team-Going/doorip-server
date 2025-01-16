package org.doorip.gateway.rdb.trip

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "allocator")
@Entity
internal class AllocatorJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocator_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set
}
