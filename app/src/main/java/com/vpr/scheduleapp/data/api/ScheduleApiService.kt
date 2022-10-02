package com.vpr.scheduleapp.data.api

import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.stations.FetchedStations
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleApiService {

    @GET("./stations_list")
    fun getAllStations() : FetchedStations

    @GET("./schedule")
    fun getScheduleBy() : Call<FetchedSchedule>
}