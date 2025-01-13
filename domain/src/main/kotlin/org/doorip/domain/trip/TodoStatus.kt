package org.doorip.domain.trip

enum class TodoStatus(
    private val description: String,
) {
    INCOMPLETE("미완료 상태"),
    COMPLETE("완료 상태"),
    ;
}
