package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.*
import com.vpr.scheduleapp.data.database.schedule.converters.Converters
import com.vpr.scheduleapp.data.database.schedule.converters.DirectionConverter
import com.vpr.scheduleapp.data.database.schedule.converters.ScheduleConverter
import com.vpr.scheduleapp.domain.model.schedule.*
//main response class
@Entity(tableName = "schedule_station", primaryKeys = ["date", "station"])
@TypeConverters(ScheduleConverter::class, DirectionConverter::class, Converters::class)
data class StationScheduleEntity(
    @ColumnInfo
    val date: String,
    @ColumnInfo
    val directions: List<DirectionEntity>,
    @ColumnInfo
    val interval_schedule: List<ScheduleEntity>,
    @ColumnInfo
    val pagination: PaginationEntity,
    @ColumnInfo
    val schedule: List<ScheduleEntity>,
    @ColumnInfo
    val schedule_direction: ScheduleDirectionEntity,
    @ColumnInfo
    val station: StationEntity
) {
    fun toStationSchedule(): StationSchedule {
        return StationSchedule(
            date = date,
            directions = directions.map { it.toDirections() },
            interval_schedule = interval_schedule.map { it.toSchedule() },
            pagination = pagination.toPagination(),
            schedule = schedule.map { it.toSchedule() },
            schedule_direction = schedule_direction.toScheduleDirection(),
            station = station.toStation(),
        )
    }
}
