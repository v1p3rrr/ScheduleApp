package com.vpr.scheduleapp.domain.model.schedule

data class FetchedSchedule(
    val date: String,
    val directions: List<Directions>,
    val interval_schedule: List<Schedule>,
    val pagination: Pagination,
    val schedule: List<Schedule>,
    val schedule_direction: ScheduleDirection,
    val station: Station
)

data class Directions(
    val code: String,
    val title: String
)

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

data class ScheduleDirection(
    val code: String,
    val title: String
)

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

data class Threadd(
    val uid: String?,
    val express_type: String?,
    val number: String?,
    val short_title: String?,
    val title: String?,
    val transport_subtype: TransportSubtype?,
    val transport_type: String?,
    val vehicle: String?
)

data class TransportSubtype(
    val code: String,
    val color: String,
    val title: String
)

data class Pagination(
    val limit: Int,
    val offset: Int,
    val total: Int
)