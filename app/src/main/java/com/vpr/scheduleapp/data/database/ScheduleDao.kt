package com.vpr.scheduleapp.data.database

import androidx.room.*
import com.vpr.scheduleapp.data.database.entity.schedule.FetchedScheduleEntity
import com.vpr.scheduleapp.data.database.entity.schedule.StationEntity
import com.vpr.scheduleapp.domain.model.schedule.Schedule

@Dao
abstract class ScheduleDao{

    @Query("SELECT * FROM fetched_schedule")
    abstract fun getAllSchedule(): List<FetchedScheduleEntity>

    @Query("SELECT * FROM station WHERE code = :stationCode LIMIT 1")
    abstract fun getStationByCode(stationCode: String): StationEntity

    @Query("SELECT * FROM fetched_schedule WHERE station = :station AND date = :date LIMIT 1")
    abstract fun getScheduleByStation(station: StationEntity, date: String): FetchedScheduleEntity?

    @Query("")
    @Transaction
    fun getScheduleByStationCode(stationCode: String, date: String): FetchedScheduleEntity?{
        val station = getStationByCode(stationCode)
        return if (station!=null) getScheduleByStation(station, date) else null
    }

    //todo how to get entry by station if its an object made with converter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSchedule(fetchedScheduleEntity: FetchedScheduleEntity)

    @Query("DELETE FROM fetched_schedule WHERE schedule IN (:id)")
    abstract suspend fun deleteSchedule(id: List<String>)

    @Update
    abstract suspend fun update(schedule: FetchedScheduleEntity)

    @Delete
    abstract suspend fun delete(schedule: FetchedScheduleEntity)
}