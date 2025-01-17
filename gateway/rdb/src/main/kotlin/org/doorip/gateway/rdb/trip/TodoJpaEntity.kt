package org.doorip.gateway.rdb.trip

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate
import org.doorip.domain.trip.TodoStatus
import org.doorip.domain.trip.TodoType
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

@Table(name = "todo")
@Entity
internal class TodoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", columnDefinition = "bigint", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "title", columnDefinition = "varchar(255)", nullable = false)
    lateinit var title: String
        protected set

    @Column(name = "end_date", columnDefinition = "date", nullable = false)
    lateinit var endDate: LocalDate
        protected set

    @Column(name = "memo", columnDefinition = "text", nullable = true)
    var memo: String? = null
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "secret", columnDefinition = "enum('OUR', 'MY')", nullable = false)
    lateinit var todoType: TodoType
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "progress", columnDefinition = "enum('incomplete', 'complete')", nullable = false)
    lateinit var todoStatus: TodoStatus
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    lateinit var trip: TripJpaEntity
        protected set

    @Cascade(value = [CascadeType.PERSIST, CascadeType.REMOVE])
    @OneToMany(mappedBy = "todo")
    val allocators: MutableList<AllocatorJpaEntity> = mutableListOf()
}
