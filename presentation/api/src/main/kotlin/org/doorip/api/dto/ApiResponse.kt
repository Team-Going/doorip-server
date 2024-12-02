package org.doorip.api.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ApiResponse<T>(
    val status: Int,
    val code: String,
    val message: String,
    val data: T?,
) {

    companion object {
        fun <T> ok(
            data: T? = null,
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity.ok(
            ApiResponse(
                status = 200,
                code = "s2000",
                message = "요청이 성공했습니다.",
                data = data,
            ),
        )

        fun <T> created(
            data: T? = null,
        ): ResponseEntity<ApiResponse<T>> = ResponseEntity.status(
            HttpStatus.CREATED,
        ).body(
            ApiResponse(
                status = 201,
                code = "s2010",
                message = "요청이 성공했습니다.",
                data = data,
            ),
        )
    }
}
