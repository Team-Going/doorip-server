package org.doorip.domain.trip

enum class TodoType(
    private val description: String,
) {
    OUR_TODO("아워 투두"),
    MY_TODO("마이 투두"),
    ;
}
