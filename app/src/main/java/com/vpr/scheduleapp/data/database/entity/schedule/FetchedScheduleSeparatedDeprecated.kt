package com.vpr.scheduleapp.data.database.entity.schedule

//import androidx.room.*
//import com.vpr.scheduleapp.data.database.converters.*
//
//@Entity(tableName = "fetched_schedule")
//data class FetchedScheduleEntity(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val date: String
//)
//
//data class FetchedScheduleWithProperties(
//    @Embedded
//    val fetchedSchedule: FetchedScheduleEntity,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "fetchedScheduleId",
//        entity = Schedule::class
//    )
//    val schedule: List<Schedule>?,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "fetchedScheduleId",
//        entity = Direction::class
//    )
//    val directions: List<Direction>?,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "fetchedScheduleId",
//        entity = Station::class
//    )
//    val station: Station?,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "fetchedScheduleId",
//        entity = ScheduleDirection::class
//    )
//    val schedule_direction: ScheduleDirection?,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "fetchedScheduleId",
//        entity = Pagination::class
//    )
//    val pagination: Pagination?
//)
//
//@Entity(tableName = "direction")
//data class Direction(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val code: String,
//    @ColumnInfo
//    val title: String,
//    @ColumnInfo
//    val fetchedScheduleId: Long
//)
//
//@Entity(tableName = "schedule")
//data class Schedule(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    var arrival: String,
//    @ColumnInfo
//    val days: String,
//    @ColumnInfo
//    var departure: String,
//    @ColumnInfo
//    val direction: String,
//    @ColumnInfo
//    val except_days: String,
//    @ColumnInfo
//    val is_fuzzy: Boolean,
//    @ColumnInfo
//    val platform: String,
//    @ColumnInfo
//    val stops: String,
//    @ColumnInfo
//    val terminal: String,
//    @ColumnInfo
//    val fetchedScheduleId: Long
//)
//
//data class ScheduleWithThreadd(
//    @Embedded
//    val schedule: Schedule,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "scheduleId",
//        entity = Threadd::class
//    )
//    val thread: Threadd
//)
//
//@Entity(tableName = "schedule_direction")
//data class ScheduleDirection(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val code: String,
//    @ColumnInfo
//    val title: String,
//    @ColumnInfo
//    val fetchedScheduleId: Long
//)
//
//@Entity(tableName = "station")
//data class Station(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val code: String,
//    @ColumnInfo
//    val popular_title: String,
//    @ColumnInfo
//    val short_title: String,
//    @ColumnInfo
//    val station_type: String,
//    @ColumnInfo
//    val station_type_name: String,
//    @ColumnInfo
//    val title: String,
//    @ColumnInfo
//    val transport_type: String,
//    @ColumnInfo
//    val type: String,
//    @ColumnInfo
//    val fetchedScheduleId: Long
//)
//
//@Entity(tableName = "thread")
//data class Threadd(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val uid: String,
//    @ColumnInfo
//    val express_type: String,
//    @ColumnInfo
//    val number: String,
//    @ColumnInfo
//    val short_title: String,
//    @ColumnInfo
//    val title: String,
//    @ColumnInfo
//    val transport_subtype: TransportSubtype,
//    @ColumnInfo
//    val transport_type: String,
//    @ColumnInfo
//    val vehicle: String,
//    @ColumnInfo
//    val scheduleId: Long
//)
//
////data class ThreaddWithTransportSubtype(
////    @Embedded
////    val thread: Threadd,
////    @Relation(
////        parentColumn = "id",
////        entityColumn = "threadId",
////        entity = TransportSubtype::class
////    )
////    val transport_subtype: TransportSubtype
////)
//
//@Entity(tableName = "transport_subtype")
//data class TransportSubtype(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val code: String,
//    @ColumnInfo
//    val color: String,
//    @ColumnInfo
//    val title: String,
//    @ColumnInfo
//    val threadId: Long
//)
//
//@Entity(tableName = "pagination")
//data class Pagination(
//    @PrimaryKey
//    val id: Long? = null,
//    @ColumnInfo
//    val limit: Int,
//    @ColumnInfo
//    val offset: Int,
//    @ColumnInfo
//    val total: Int,
//    @ColumnInfo
//    val fetchedScheduleId: Long
//)