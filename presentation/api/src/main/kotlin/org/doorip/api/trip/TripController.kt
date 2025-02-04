package org.doorip.api.trip

import org.doorip.api.dto.ApiResponse
import org.doorip.api.trip.dto.TripCreateRequest
import org.doorip.api.trip.dto.TripCreateResponse
import org.doorip.api.trip.dto.toPropensityTag
import org.doorip.api.trip.dto.toResponse
import org.doorip.core.trip.TripUseCase
import org.doorip.presentation.support.auth.AuthenticatedUser
import org.doorip.presentation.support.auth.NeedAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
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
            startDate = request.startDate,
            endDate = request.endDate,
            styles = request.toPropensityTag(),
        ).toResponse()

        return ApiResponse.created(response)
    }
}
