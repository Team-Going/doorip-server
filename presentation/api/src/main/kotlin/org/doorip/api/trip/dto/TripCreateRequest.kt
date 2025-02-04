package org.doorip.api.trip.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import org.doorip.domain.trip.PropensityTag

data class TripCreateRequest(
    val title: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    val startDate: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    val endDate: LocalDate,
    val styleA: Int,
    val styleB: Int,
    val styleC: Int,
    val styleD: Int,
    val styleE: Int,
)

fun TripCreateRequest.toPropensityTag(): PropensityTag {
    return PropensityTag(
        styles = listOfNotNull(styleA, styleB, styleC, styleD, styleE),
    )
}
