package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.ScheduleDirection

@Entity(tableName = "schedule_direction")
data class ScheduleDirectionEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val title: String
) {
    fun toScheduleDirection(): ScheduleDirection {
        return ScheduleDirection(
            code = code,
            title = title
        )
    }
}