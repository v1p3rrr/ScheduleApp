package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.vpr.scheduleapp.domain.model.schedule.Schedule

data class StationScheduleWithSchedule(
    @Embedded
    val fetchedSchedule: StationScheduleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "fetchedScheduleId",
        entity = Schedule::class
    )
    val schedule: List<Schedule>?
)