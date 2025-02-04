package org.doorip.api.user

import org.doorip.api.dto.ApiResponse
import org.doorip.api.user.dto.UserGetProfileResponse
import org.doorip.api.user.dto.UserUpdateProfileRequest
import org.doorip.api.user.dto.toResponse
import org.doorip.core.user.ProfileUseCase
import org.doorip.presentation.support.auth.AuthenticatedUser
import org.doorip.presentation.support.auth.NeedAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class UserController(
    private val profileUseCase: ProfileUseCase,
) {

    @NeedAuthentication
    @GetMapping("/api/users/profile")
    fun getProfile(
        authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<ApiResponse<UserGetProfileResponse>> {
        val response = profileUseCase.getProfile(authenticatedUser.userId)
            .toResponse()

        return ApiResponse.ok(response)
    }

    @NeedAuthentication
    @PatchMapping("/api/users/profile")
    fun updateProfile(
        authenticatedUser: AuthenticatedUser,
        @RequestBody request: UserUpdateProfileRequest,
    ): ResponseEntity<ApiResponse<Unit>> {
        profileUseCase.updateProfile(
            userId = authenticatedUser.userId,
            name = request.name,
            intro = request.intro,
        )

        return ApiResponse.ok()
    }
}
