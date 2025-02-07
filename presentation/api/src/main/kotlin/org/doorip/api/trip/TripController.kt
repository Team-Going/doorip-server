package org.doorip.api.trip

import org.doorip.api.dto.ApiResponse
import org.doorip.api.trip.dto.TripCreateRequest
import org.doorip.api.trip.dto.TripCreateResponse
import org.doorip.api.trip.dto.TripEntryRequest
import org.doorip.api.trip.dto.TripEntryResponse
import org.doorip.api.trip.dto.TripVerifyInvitationCodeRequest
import org.doorip.api.trip.dto.TripVerifyInvitationCodeResponse
import org.doorip.api.trip.dto.toPropensityTag
import org.doorip.api.trip.dto.toResponse
import org.doorip.api.trip.dto.toVerifyResponse
import org.doorip.core.trip.TripUseCase
import org.doorip.domain.trip.TripId
import org.doorip.presentation.support.auth.AuthenticatedUser
import org.doorip.presentation.support.auth.NeedAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class TripController(
    private val tripUseCase: TripUseCase,
) {

    @NeedAuthentication
    @PostMapping("/api/trips")
    fun createTrip(
        authenticatedUser: AuthenticatedUser,
        @RequestBody request: TripCreateRequest,
    ): ResponseEntity<ApiResponse<TripCreateResponse>> {
        val response = tripUseCase.createTrip(
            userId = authenticatedUser.userId,
            title = request.title,
            startAt = request.startDate,
            endAt = request.endDate,
            styles = request.toPropensityTag(),
        ).toResponse()

        return ApiResponse.created(response)
    }

    @NeedAuthentication
    @PostMapping("/api/trips/verify")
    fun verifyInvitationCode(
        @RequestBody request: TripVerifyInvitationCodeRequest,
    ): ResponseEntity<ApiResponse<TripVerifyInvitationCodeResponse>> {
        val response = tripUseCase.verifyInvitationCode(request.code)
            .toVerifyResponse()

        return ApiResponse.ok(response)
    }

    @NeedAuthentication
    @PostMapping("/api/trips/{tripId}/entry")
    fun entryTrip(
        authenticatedUser: AuthenticatedUser,
        @PathVariable tripId: Long,
        @RequestBody request: TripEntryRequest,
    ): ResponseEntity<ApiResponse<TripEntryResponse>> {
        tripUseCase.entryTrip(
            userId = authenticatedUser.userId,
            tripId = TripId(tripId),
            styles = request.toPropensityTag(),
        )

        return ApiResponse.ok(TripEntryResponse(tripId))
    }
}
