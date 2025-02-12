package org.doorip.api.trip

import org.doorip.api.dto.ApiResponse
import org.doorip.api.trip.dto.TripCreateRequest
import org.doorip.api.trip.dto.TripCreateResponse
import org.doorip.api.trip.dto.TripEntryRequest
import org.doorip.api.trip.dto.TripEntryResponse
import org.doorip.api.trip.dto.TripGetsResponse
import org.doorip.api.trip.dto.TripResponse
import org.doorip.api.trip.dto.TripVerifyInvitationCodeRequest
import org.doorip.api.trip.dto.toCreateResponse
import org.doorip.api.trip.dto.toPropensityTag
import org.doorip.api.trip.dto.toResponse
import org.doorip.core.trip.TripUseCase
import org.doorip.core.user.ProfileUseCase
import org.doorip.domain.InvalidRequestValueException
import org.doorip.domain.trip.Progress
import org.doorip.domain.trip.TripId
import org.doorip.presentation.support.auth.AuthenticatedUser
import org.doorip.presentation.support.auth.NeedAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Controller
class TripController(
    private val profileUseCase: ProfileUseCase,
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
        ).toCreateResponse()

        return ApiResponse.created(response)
    }

    @NeedAuthentication
    @PostMapping("/api/trips/verify")
    fun verifyInvitationCode(
        @RequestBody request: TripVerifyInvitationCodeRequest,
    ): ResponseEntity<ApiResponse<TripResponse>> {
        val response = tripUseCase.verifyInvitationCode(request.code)
            .toResponse()

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

    @NeedAuthentication
    @GetMapping("/api/trips")
    fun getTrips(
        authenticatedUser: AuthenticatedUser,
        @RequestParam(value = "progress") progress: String,
    ): ResponseEntity<ApiResponse<TripGetsResponse>> {
        val user = profileUseCase.getProfile(authenticatedUser.userId)

        val enumProgress = Progress.getProgress(progress) ?: throw InvalidRequestValueException

        val trips = tripUseCase.getTrips(authenticatedUser.userId, enumProgress)

        val response = TripGetsResponse(
            name = user.name,
            trips = trips.map { it.toResponse() },
        )

        return ApiResponse.ok(response)
    }
}
