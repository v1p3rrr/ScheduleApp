package com.vpr.scheduleapp.data.api

import com.vpr.scheduleapp.BuildConfig
import com.vpr.scheduleapp.data.api.dto.schedule_between.ScheduleBetweenDTO
import com.vpr.scheduleapp.data.api.dto.station_schedule.StationScheduleDTO
import com.vpr.scheduleapp.data.api.dto.stations.CountryDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApiService {

    private val API_KEY: String
        get() = BuildConfig.YANDEX_RASP_KEY

    @GET("./stations_list/?apikey=d98d9bbd-e23f-48af-86b4-dbb3ba55db9e&lang=ru_RU&format=json")
    fun getAllStations(): Call<List<CountryDTO>>

    @GET("./schedule/?transport_types=suburban")
     fun getScheduleOnStation(
        @Query("apikey") apikey: String = API_KEY,
        @Query("station") station_code: String = "s9879631",
        @Query("date") date: String,
        @Query("direction") direction: String = "На%20Москву",
        @Query("event") event: String = "departure",
        @Query("lang") lang: String = "ru_RU"
    ): Call<StationScheduleDTO>

    @GET("./search/?transport_types=suburban")
    suspend fun getScheduleByStationCodeAndDate(
        @Query("apikey") apikey: String = API_KEY,
        @Query("from") from_code: String = "s9879631",
        @Query("to") to_code: String = "s9600771",
        @Query("date") date: String = "2022-11-22",
        @Query("lang") lang: String = "ru_RU"
    ): ScheduleBetweenDTO
}