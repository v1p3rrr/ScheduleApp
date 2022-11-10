package com.vpr.scheduleapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM fetched_schedule")
    fun getAllSchedule() : List<FetchedSchedule>

    @Query("SELECT * FROM fetched_schedule WHERE station = :station")
    fun getScheduleByStation(station: String) : List<FetchedSchedule>
    //todo how to get entry by station if its an object made with converter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(fetchedSchedule: FetchedSchedule)
}