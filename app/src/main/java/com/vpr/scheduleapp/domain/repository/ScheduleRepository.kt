package com.vpr.scheduleapp.domain.repository

import com.vpr.scheduleapp.domain.model.schedule.Segment
import com.vpr.scheduleapp.domain.model.schedule.StationSchedule
import com.vpr.scheduleapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getScheduleFromDb(from: String, to: String, date: String): List<Segment?>
    fun getScheduleByStationCodeAndDate(
        from: String,
        to: String,
        date: String
    ): Flow<Resource<List<Segment?>>>
    //todo
}