package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.ScheduleDirectionEntity
import com.vpr.scheduleapp.domain.model.schedule.ScheduleDirection

data class ScheduleDirectionDTO(
    val code: String,
    val title: String
) {
    fun toScheduleDirection(): ScheduleDirection {
        return ScheduleDirection(
            code = code,
            title = title
        )
    }

    fun toScheduleDirectionEntity(): ScheduleDirectionEntity {
        return ScheduleDirectionEntity(
            code = code,
            title = title
        )
    }
}
