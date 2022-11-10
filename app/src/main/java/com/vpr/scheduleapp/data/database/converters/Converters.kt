package com.vpr.scheduleapp.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vpr.scheduleapp.data.model.schedule.*

class Converters {
    //todo do i need converters for objects?
    @TypeConverter
    fun carrierToString(carrier: Carrier): String = Gson().toJson(carrier)

    @TypeConverter
    fun stringToCarrier(string: String): Carrier = Gson().fromJson(string, Carrier::class.java)

    @TypeConverter
    fun transportSubtypeToString(transportSubtype: TransportSubtype): String = Gson().toJson(transportSubtype)

    @TypeConverter
    fun stringToTransportSubtype(string: String): TransportSubtype = Gson().fromJson(string, TransportSubtype::class.java)

    @TypeConverter
    fun threadToString(thread: Thread): String = Gson().toJson(thread)

    @TypeConverter
    fun stringToScheduleThread(string: String): Thread = Gson().fromJson(string, Thread::class.java)

    @TypeConverter
    fun scheduleDirectionToString(scheduleDirection: ScheduleDirection): String = Gson().toJson(scheduleDirection)

    @TypeConverter
    fun stringToScheduleDirection(string: String): ScheduleDirection = Gson().fromJson(string, ScheduleDirection::class.java)

    @TypeConverter
    fun stationToString(station: Station): String = Gson().toJson(station)

    @TypeConverter
    fun stringToStation(string: String): Station = Gson().fromJson(string, Station::class.java)

    @TypeConverter
    fun paginationToString(pagination: Pagination): String = Gson().toJson(pagination)

    @TypeConverter
    fun stringToPagination(string: String): Pagination = Gson().fromJson(string, Pagination::class.java)

    @TypeConverter
    fun codesToString(codes: Codes): String = Gson().toJson(codes)

    @TypeConverter
    fun stringToCodes(string: String): Codes = Gson().fromJson(string, Codes::class.java)

}