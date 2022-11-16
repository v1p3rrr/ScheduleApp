package com.vpr.scheduleapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vpr.scheduleapp.data.database.converters.*
import com.vpr.scheduleapp.data.database.entity.schedule.FetchedScheduleEntity
import com.vpr.scheduleapp.data.database.entity.schedule.*


@Database(entities = [FetchedScheduleEntity::class, DirectionEntity::class, ScheduleEntity::class, ScheduleDirectionEntity::class, StationEntity::class, ThreaddEntity::class, TransportSubtypeEntity::class, PaginationEntity::class], version = 1)
@TypeConverters(ScheduleConverter::class, DirectionConverter::class, Converters::class)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun scheduleDao() : ScheduleDao

    companion object {
        const val DB_NAME = "room_database"
    }
}