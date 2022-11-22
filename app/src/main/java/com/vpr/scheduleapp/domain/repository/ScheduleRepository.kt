package com.vpr.scheduleapp.domain.repository

import com.vpr.scheduleapp.domain.model.schedule.StationSchedule
import com.vpr.scheduleapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getScheduleFromDb(stationCode: String, date: String): StationSchedule?
    fun getScheduleByStationCodeAndDate(
        stationCode: String,
        date: String
    ): Flow<Resource<StationSchedule>>
    //todo
}