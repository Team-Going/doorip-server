package org.doorip.domain.trip

enum class Progress(
    private val description: String,
) {
    INCOMPLETE("incomplete"),
    COMPLETE("complete"),
    ;

    companion object {
        fun getProgress(description: String): Progress? {
            return entries.find { it.description == description }
        }
    }
}
