package org.doorip.api.auth

import org.doorip.api.auth.dto.AuthResponse
import org.doorip.api.auth.dto.AuthSignInRequest
import org.doorip.api.auth.dto.AuthSignInResponse
import org.doorip.api.auth.dto.AuthSignUpRequest
import org.doorip.api.auth.dto.toResponse
import org.doorip.api.dto.ApiResponse
import org.doorip.core.auth.AuthUseCase
import org.doorip.presentation.support.auth.AuthenticatedUser
import org.doorip.presentation.support.auth.NeedAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@Controller
class AuthController(
    private val authUseCase: AuthUseCase,
) {

    @PostMapping("/api/users/signin")
    fun signIn(
        @RequestHeader("Authorization") token: String,
        @RequestBody request: AuthSignInRequest,
    ): ResponseEntity<ApiResponse<AuthSignInResponse>> {
        val response = authUseCase.signIn(
            token = token,
            platform = request.platform,
        ).toResponse()

        return ApiResponse.ok(response)
    }

    @PostMapping("/api/users/signup")
    fun signUp(
        @RequestHeader("Authorization") token: String,
        @RequestBody request: AuthSignUpRequest,
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        val response = authUseCase.signUp(
            token = token,
            platform = request.platform,
            name = request.name,
            intro = request.intro,
        ).toResponse()

        return ApiResponse.created(response)
    }

    @NeedAuthentication
    @PatchMapping("/api/users/signout")
    fun signOut(
        authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<ApiResponse<Unit>> {
        authUseCase.signOut(authenticatedUser.userId)

        return ApiResponse.ok()
    }

    @PostMapping("/api/users/reissue")
    fun reissue(
        @RequestHeader("Authorization") refreshToken: String,
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        val response = authUseCase.reissue(refreshToken)
            .toResponse()

        return ApiResponse.ok(response)
    }

    @NeedAuthentication
    @DeleteMapping("/api/users/withdraw")
    fun withdraw(
        authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<ApiResponse<Unit>> {
        authUseCase.withdraw(authenticatedUser.userId)

        return ApiResponse.ok()
    }
}
