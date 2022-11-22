package com.vpr.scheduleapp.domain.model.stations


data class Country(
    val code: String,
    val regions: List<Region>,
    val title: String
)

data class Region(
    val code: String,
    val settlements: List<Settlement>,
    val title: String
)

data class Settlement(
    val code: String,
    val stations: List<Station>,
    val title: String
)

data class Station(
    val code: String,
    val direction: String,
    val latitude: Double?, //double
    val longitude: Double?, //double
    val station_type: String,
    val title: String,
    val transport_type: String
)