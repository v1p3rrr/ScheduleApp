package com.vpr.scheduleapp.domain.model.schedule

data class Schedule(
    var arrival: String,
    val days: String,
    var departure: String,
    val travel_time: String,
    val direction: String,
    val except_days: String?,
    val is_fuzzy: Boolean,
    val platform: String?,
    val stops: String?,
    val terminal: String?,
    val thread: Threadd
)