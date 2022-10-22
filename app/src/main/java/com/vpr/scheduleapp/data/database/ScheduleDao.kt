package com.vpr.scheduleapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM fetched_schedule")
    fun getSchedule() : List<FetchedSchedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(fetchedSchedule: FetchedSchedule)
}