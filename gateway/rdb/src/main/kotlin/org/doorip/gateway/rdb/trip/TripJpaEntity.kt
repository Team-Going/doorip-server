package org.doorip.gateway.rdb.trip

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "trip")
@Entity
internal class TripJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "code", columnDefinition = "varchar(255)", nullable = false)
    lateinit var code: String
        protected set

    @Column(name = "title", columnDefinition = "varchar(255)", nullable = false)
    lateinit var title: String
        protected set

    @Column(name = "start_date", columnDefinition = "date", nullable = false)
    lateinit var startDate: LocalDate
        protected set

    @Column(name = "end_date", columnDefinition = "date", nullable = false)
    lateinit var endDate: LocalDate
        protected set
}