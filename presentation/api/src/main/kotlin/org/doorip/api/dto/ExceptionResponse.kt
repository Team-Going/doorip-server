package org.doorip.api.dto

data class ExceptionResponse(
    val status: Int,
    val code: String,
    val message: String?,
)
