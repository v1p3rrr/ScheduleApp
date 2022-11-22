package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Schedule

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo
    val arrival: String,
    @ColumnInfo
    val days: String,
    @ColumnInfo
    val departure: String,
    @ColumnInfo
    val travel_time: String,
    @ColumnInfo
    val direction: String,
    @ColumnInfo
    val except_days: String?,
    @ColumnInfo
    val is_fuzzy: Boolean,
    @ColumnInfo
    val platform: String?,
    @ColumnInfo
    val stops: String?,
    @ColumnInfo
    val terminal: String?,
    @ColumnInfo
    val thread: ThreadEntity
) {
    fun toSchedule(): Schedule {
        return Schedule(
            arrival = arrival,
            days = days,
            departure = departure,
            direction = direction,
            travel_time = travel_time,
            except_days = except_days,
            is_fuzzy = is_fuzzy,
            platform = platform,
            stops = stops,
            terminal = terminal,
            thread = thread.toThreadd()
        )
    }
}