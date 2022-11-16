package com.vpr.scheduleapp.data.remote

import com.vpr.scheduleapp.data.remote.dto.schedule.FetchedScheduleDTO
import com.vpr.scheduleapp.data.remote.dto.stations.CountryDTO
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleApiService {

    @GET("./stations_list/?apikey=d98d9bbd-e23f-48af-86b4-dbb3ba55db9e&lang=ru_RU&format=json")
    fun getAllStations() : Call<List<CountryDTO>>

    @GET("./schedule/?station=s9879631&transport_types=suburban&direction=На%20Москву&apikey=d98d9bbd-e23f-48af-86b4-dbb3ba55db9e&date=2022-11-17&lang=ru_RU&event=departure")
    fun getScheduleBy() : Call<FetchedScheduleDTO>
}