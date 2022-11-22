package com.vpr.scheduleapp.data.database.stations

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vpr.scheduleapp.data.database.schedule.converters.Converters
import com.vpr.scheduleapp.data.database.schedule.converters.DirectionConverter
import com.vpr.scheduleapp.data.database.schedule.converters.ScheduleConverter
import com.vpr.scheduleapp.data.database.stations.converters.RegionConverter
import com.vpr.scheduleapp.data.database.stations.converters.SettlementConverter
import com.vpr.scheduleapp.data.database.stations.converters.StationConverter
import com.vpr.scheduleapp.data.database.stations.entity.*


@Database(entities = [CountryEntity::class, RegionEntity::class, SettlementEntity::class, StationEntity::class], version = 1)
@TypeConverters(RegionConverter::class, SettlementConverter::class, StationConverter::class)
abstract class StationsDatabase : RoomDatabase() {

    abstract fun stationsDao() : StationsDao

    companion object {
        const val DB_NAME = "stations_database"
    }
}