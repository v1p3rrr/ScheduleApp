package com.vpr.scheduleapp.data.database.schedule

import androidx.room.*
import com.vpr.scheduleapp.data.database.schedule.entity.StationEntity
import com.vpr.scheduleapp.data.database.schedule.entity.StationScheduleEntity

@Dao
abstract class ScheduleDao {

    @Query("SELECT * FROM schedule_station")
    abstract fun getAllSchedule(): List<StationScheduleEntity>

    @Query("SELECT * FROM station WHERE code = :stationCode LIMIT 1")
    abstract fun getStationByCode(stationCode: String): StationEntity?

    @Query("SELECT * FROM schedule_station WHERE station = :station AND date = :date LIMIT 1")
    abstract fun getScheduleByStation(station: StationEntity, date: String): StationScheduleEntity?

    @Query("")
    @Transaction
    fun getScheduleByStationCode(stationCode: String, date: String): StationScheduleEntity? {
        val station = getStationByCode(stationCode)
        return if (station != null) getScheduleByStation(station, date) else null
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSchedule(stationScheduleEntity: StationScheduleEntity)

    @Query("")
    @Transaction
    suspend fun deleteSchedule(stationCode: String, date: String) {
        val station = getStationByCode(stationCode)
        if (station != null) {
            val schedule = getScheduleByStation(station, date)
            if (schedule != null) delete(schedule)
        }
    }

    @Update
    abstract suspend fun update(schedule: StationScheduleEntity)

    @Delete
    abstract suspend fun delete(schedule: StationScheduleEntity)
}