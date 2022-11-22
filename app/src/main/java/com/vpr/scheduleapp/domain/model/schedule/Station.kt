package com.vpr.scheduleapp.domain.model.schedule

data class Station(
    val code: String,
    val popular_title: String?,
    val short_title: String?,
    val station_type: String?,
    val station_type_name: String?,
    val title: String?,
    val transport_type: String?,
    val type: String?
)