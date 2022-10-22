package com.vpr.scheduleapp.data.model.schedule

import androidx.room.*
import com.vpr.scheduleapp.data.database.converters.*

@Entity(tableName = "fetched_schedule", primaryKeys = ["date", "station"])
@TypeConverters(ScheduleConverter::class, DirectionConverter::class, Converters::class)
data class FetchedSchedule(
    @ColumnInfo
    val date: String,
    @ColumnInfo
    val directions: List<Direction>,
    @ColumnInfo
    val interval_schedule: List<Schedule>,
    @ColumnInfo
    val pagination: Pagination,
    @ColumnInfo
    val schedule: List<Schedule>,
    @ColumnInfo
    val schedule_direction: ScheduleDirection,
    @ColumnInfo
    val station: Station
)

@Entity(tableName = "direction")
data class Direction(
    @PrimaryKey
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val title: String
)

@Entity(tableName = "schedule", primaryKeys = ["thread", "departure", "arrival"])
data class Schedule(
    @ColumnInfo
    val arrival: String,
    @ColumnInfo
    val days: String,
    @ColumnInfo
    val departure: String,
    @ColumnInfo
    val direction: String,
    @ColumnInfo
    val except_days: String,
    @ColumnInfo
    val is_fuzzy: Boolean,
    @ColumnInfo
    val platform: String,
    @ColumnInfo
    val stops: String,
    @ColumnInfo
    val terminal: String,
    @ColumnInfo
    val thread: Thread
)

@Entity(tableName = "schedule_direction")
data class ScheduleDirection(
    @PrimaryKey
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val title: String
)

@Entity(tableName = "station")
data class Station(
    @PrimaryKey
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val popular_title: String,
    @ColumnInfo
    val short_title: String,
    @ColumnInfo
    val station_type: String,
    @ColumnInfo
    val station_type_name: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val transport_type: String,
    @ColumnInfo
    val type: String
)

@Entity(tableName = "thread")
data class Thread(
    @PrimaryKey
    @ColumnInfo
    val uid: String,
    @ColumnInfo
    val carrier: Carrier,
    @ColumnInfo
    val express_type: String,
    @ColumnInfo
    val number: String,
    @ColumnInfo
    val short_title: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val transport_subtype: TransportSubtype,
    @ColumnInfo
    val transport_type: String,
    @ColumnInfo
    val vehicle: String
)

@Entity(tableName = "transport_subtype")
data class TransportSubtype(
    @PrimaryKey
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val color: String,
    @ColumnInfo
    val title: String
)

@Entity(tableName = "codes", primaryKeys = ["iata", "icao", "sirena"])
data class Codes(
    @ColumnInfo
    val iata: String,
    @ColumnInfo
    val icao: String,
    @ColumnInfo
    val sirena: String
)

@Entity(tableName = "carrier")
data class Carrier(
    @PrimaryKey
    @ColumnInfo
    val code: Int,
    @ColumnInfo
    val codes: Codes,
    @ColumnInfo
    val title: String
)

@Entity(tableName = "pagination", primaryKeys = ["limit", "offset", "total"])
data class Pagination(
    @ColumnInfo
    val limit: Int,
    @ColumnInfo
    val offset: Int,
    @ColumnInfo
    val total: Int
)