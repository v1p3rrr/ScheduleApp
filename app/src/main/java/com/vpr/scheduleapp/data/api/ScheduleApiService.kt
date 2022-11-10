package com.vpr.scheduleapp.data.api

import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.stations.FetchedStations
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleApiService {

    @GET("./stations_list/?apikey=d98d9bbd-e23f-48af-86b4-dbb3ba55db9e&lang=ru_RU&format=json")
    fun getAllStations() : Call<FetchedStations>

    @GET("./schedule/?station=180237&transport_types=suburban&direction=На%20Москву&apikey=d98d9bbd-e23f-48af-86b4-dbb3ba55db9e&date=2022-11-12&lang=ru_RU&event=departure&system=esr")
    fun getScheduleBy() : Call<FetchedSchedule>
}