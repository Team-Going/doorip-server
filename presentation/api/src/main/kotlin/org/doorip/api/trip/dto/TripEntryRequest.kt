package org.doorip.api.trip.dto

import org.doorip.domain.trip.PropensityTag

data class TripEntryRequest(
    val styleA: Int,
    val styleB: Int,
    val styleC: Int,
    val styleD: Int,
    val styleE: Int,
)

fun TripEntryRequest.toPropensityTag(): PropensityTag {
    return PropensityTag(
        styles = listOf(styleA, styleB, styleC, styleD, styleE),
    )
}
