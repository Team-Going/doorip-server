package org.doorip.domain.trip

import java.time.LocalDateTime

@JvmInline
value class TodoId(
    val value: Long,
)

data class Todo(
    val id: TodoId,
    val title: String,
    val endAt: LocalDateTime,
    val memo: String?,
    val todoOption: TodoOption,
    val allocators: List<Allocator>,
)
