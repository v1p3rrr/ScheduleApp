package com.vpr.scheduleapp.data.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM fetched_schedule")
    fun getSchedule()

}