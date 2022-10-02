package com.vpr.scheduleapp.data.model.stations

data class FetchedStations(
    val countries: List<Country>
)

data class Country(
    val codes: Codes,
    val regions: List<Region>,
    val title: String
)

data class Region(
    val codes: Codes,
    val settlements: List<Settlement>,
    val title: String
)

data class Settlement(
    val codes: Codes,
    val stations: List<Station>,
    val title: String
)

data class Station(
    val codes: CodesXXX,
    val direction: String,
    val latitude: Double,
    val longitude: Double,
    val station_type: String,
    val title: String,
    val transport_type: String
)

data class Codes(
    val yandex_code: String
)

data class CodesXXX(
    val esr_code: String,
    val yandex_code: String
)