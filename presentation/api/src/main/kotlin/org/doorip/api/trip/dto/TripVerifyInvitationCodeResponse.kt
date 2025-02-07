package org.doorip.api.trip.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import org.doorip.domain.trip.Trip

data class TripVerifyInvitationCodeResponse(
    val tripId: Long,
    val title: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    val startDate: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    val endDate: LocalDate,
    val day: Int,
)

fun Trip.toVerifyResponse(): TripVerifyInvitationCodeResponse = TripVerifyInvitationCodeResponse(
    tripId = id.value,
    title = title,
    startDate = startAt,
    endDate = endAt,
    day = ChronoUnit.DAYS.between(LocalDate.now(), startAt).toInt(),
)
