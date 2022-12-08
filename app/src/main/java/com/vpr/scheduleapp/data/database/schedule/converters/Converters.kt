package com.vpr.scheduleapp.data.database.schedule.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vpr.scheduleapp.data.database.schedule.entity.*

class Converters {

    @TypeConverter
    fun transportSubtypeToString(transportSubtype: TransportSubtypeEntity): String = Gson().toJson(transportSubtype)

    @TypeConverter
    fun stringToTransportSubtype(string: String): TransportSubtypeEntity = Gson().fromJson(string, TransportSubtypeEntity::class.java)

    @TypeConverter
    fun threadToString(thread: ThreadEntity): String = Gson().toJson(thread)

    @TypeConverter
    fun stringToScheduleThread(string: String): ThreadEntity = Gson().fromJson(string, ThreadEntity::class.java)

    @TypeConverter
    fun scheduleDirectionToString(scheduleDirection: ScheduleDirectionEntity): String = Gson().toJson(scheduleDirection)

    @TypeConverter
    fun stringToScheduleDirection(string: String): ScheduleDirectionEntity = Gson().fromJson(string, ScheduleDirectionEntity::class.java)

    @TypeConverter
    fun stationToString(station: StationEntity): String = Gson().toJson(station)

    @TypeConverter
    fun stringToStation(string: String): StationEntity = Gson().fromJson(string, StationEntity::class.java)

    @TypeConverter
    fun paginationToString(pagination: PaginationEntity): String = Gson().toJson(pagination)

    @TypeConverter
    fun stringToPagination(string: String): PaginationEntity = Gson().fromJson(string, PaginationEntity::class.java)

    @TypeConverter
    fun segmentToString(segment: SegmentScheduleEntity): String = Gson().toJson(segment)

    @TypeConverter
    fun stringToSegment(string: String): SegmentScheduleEntity = Gson().fromJson(string, SegmentScheduleEntity::class.java)


    @TypeConverter
    fun scheduleToString(schedule: ScheduleEntity): String = Gson().toJson(schedule)

    @TypeConverter
    fun stringToSchedule(string: String): ScheduleEntity = Gson().fromJson(string, ScheduleEntity::class.java)


    @TypeConverter
    fun directionToString(direction: DirectionEntity): String = Gson().toJson(direction)

    @TypeConverter
    fun stringToDirection(string: String): DirectionEntity = Gson().fromJson(string, DirectionEntity::class.java)

}