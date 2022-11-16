package com.vpr.scheduleapp.domain.repository

import com.vpr.scheduleapp.domain.model.schedule.FetchedSchedule

interface ScheduleRepository {
    fun getScheduleFromDb(stationCode: String, date: String): FetchedSchedule?
    //todo
}