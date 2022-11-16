package com.vpr.scheduleapp.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vpr.scheduleapp.data.database.entity.schedule.*

class Converters {
    //todo do i need converters for objects?

    @TypeConverter
    fun transportSubtypeToString(transportSubtype: TransportSubtypeEntity): String = Gson().toJson(transportSubtype)

    @TypeConverter
    fun stringToTransportSubtype(string: String): TransportSubtypeEntity = Gson().fromJson(string, TransportSubtypeEntity::class.java)

    @TypeConverter
    fun threadToString(thread: ThreaddEntity): String = Gson().toJson(thread)

    @TypeConverter
    fun stringToScheduleThread(string: String): ThreaddEntity = Gson().fromJson(string, ThreaddEntity::class.java)

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

}