package org.doorip.api

import org.doorip.api.dto.ApiResponse
import org.doorip.core.TestService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestController(
    private val testService: TestService,
) {

    @ResponseBody
    @GetMapping("/api/test")
    fun test() = "doorip ok"

    @GetMapping("/api/test/ok")
    fun ok(): ResponseEntity<ApiResponse<Unit>> {
        return ApiResponse.ok()
    }

    @ResponseBody
    @GetMapping("/api/test/ex")
    fun exception() {
        testService.throwDooripException()
    }
}
