package com.vpr.scheduleapp.domain.model.schedule

data class StationSchedule(
    val date: String,
    val directions: List<Directions>,
    val interval_schedule: List<Schedule>,
    val pagination: Pagination,
    val schedule: List<Schedule>,
    val schedule_direction: ScheduleDirection,
    val station: Station
)
