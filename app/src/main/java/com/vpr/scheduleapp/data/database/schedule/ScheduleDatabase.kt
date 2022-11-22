package com.vpr.scheduleapp.data.database.schedule

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vpr.scheduleapp.data.database.schedule.converters.Converters
import com.vpr.scheduleapp.data.database.schedule.converters.DirectionConverter
import com.vpr.scheduleapp.data.database.schedule.converters.ScheduleConverter
import com.vpr.scheduleapp.data.database.schedule.entity.*


@Database(entities = [StationScheduleEntity::class, DirectionEntity::class, ScheduleEntity::class, ScheduleDirectionEntity::class, StationEntity::class, ThreadEntity::class, TransportSubtypeEntity::class, PaginationEntity::class, SegmentEntity::class, ScheduleBetweenEntity::class], version = 1)
@TypeConverters(ScheduleConverter::class, DirectionConverter::class, Converters::class)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun scheduleDao() : ScheduleDao

    companion object {
        const val DB_NAME = "schedule_database"
    }
}