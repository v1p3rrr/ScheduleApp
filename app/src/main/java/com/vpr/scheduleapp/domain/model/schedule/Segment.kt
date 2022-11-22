package com.vpr.scheduleapp.domain.model.schedule

data class Segment(
    val arrival: String,
    val arrival_platform: String,
    val arrival_terminal: String,
    val departure: String,
    val departure_platform: String,
    val departure_terminal: String,
    val duration: Int,
    val from: Station,
    val to: Station,
    val has_transfers: Boolean,
    val start_date: String,
    val stops: String,
    val thread: Threadd
)