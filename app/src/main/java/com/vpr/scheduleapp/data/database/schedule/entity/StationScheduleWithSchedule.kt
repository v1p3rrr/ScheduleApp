package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.vpr.scheduleapp.domain.model.schedule.Schedule

data class StationScheduleWithSchedule(
    @Embedded
    val stationSchedule: StationScheduleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "stationScheduleId",
        entity = Schedule::class
    )
    val schedule: List<Schedule>?
)