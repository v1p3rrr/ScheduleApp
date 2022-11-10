package com.vpr.scheduleapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vpr.scheduleapp.data.database.converters.*
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.schedule.Direction
import com.vpr.scheduleapp.data.model.schedule.Schedule
import com.vpr.scheduleapp.data.model.schedule.ScheduleDirection
import com.vpr.scheduleapp.data.model.schedule.Station
import com.vpr.scheduleapp.data.model.schedule.Thread
import com.vpr.scheduleapp.data.model.schedule.TransportSubtype
import com.vpr.scheduleapp.data.model.schedule.Codes
import com.vpr.scheduleapp.data.model.schedule.Carrier
import com.vpr.scheduleapp.data.model.schedule.Pagination


@Database(entities = [FetchedSchedule::class, Direction::class, Schedule::class, ScheduleDirection::class, Station::class, Thread::class, TransportSubtype::class, Codes::class, Carrier::class, Pagination::class], version = 1)
@TypeConverters(ScheduleConverter::class, DirectionConverter::class, Converters::class)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun scheduleDao() : ScheduleDao

    companion object {
        const val DB_NAME = "room_database"
    }
}