package org.doorip.domain.trip

@JvmInline
value class AllocatorId(
    val value: Long,
)

data class Allocator(
    val id: AllocatorId,
)
