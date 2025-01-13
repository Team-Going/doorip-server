package org.doorip.domain.trip

@JvmInline
value class ParticipantId(
    val value: Long,
)

data class Participant(
    val id: ParticipantId,
    val propensityTag: PropensityTag,
)
