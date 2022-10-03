package com.vpr.scheduleapp.data.api

import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.stations.FetchedStations
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleApiService {

    @GET("./stations_list")
    fun getAllStations() : FetchedStations

    @GET("./schedule/?apikey=d98d9bbd-e23f-48af-86b4-dbb3ba55db9e&station=s9600213&transport_types=suburban&direction=на%20Москву")
    fun getScheduleBy() : Call<FetchedSchedule>
}