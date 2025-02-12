package org.doorip.api.trip.dto

data class TripGetsResponse(
    val name: String,
    val trips: List<TripResponse>,
)
