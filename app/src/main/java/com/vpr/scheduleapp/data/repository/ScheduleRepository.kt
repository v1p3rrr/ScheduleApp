package com.vpr.scheduleapp.data.repository

import android.util.Log
import com.vpr.scheduleapp.data.api.ApiCallback
import com.vpr.scheduleapp.data.api.ScheduleApiService
import com.vpr.scheduleapp.data.database.ScheduleDatabase
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.stations.FetchedStations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepository @Inject constructor(private val scheduleDatabase: ScheduleDatabase, private val scheduleApiService: ScheduleApiService) {

    private val dao = scheduleDatabase.scheduleDao()

    fun getSchedule(station: String) : List<FetchedSchedule> {
        insertScheduleFromApiToDb()
        return getScheduleFromDb(station)
        //todo sequentially
    }

    fun getScheduleFromDb(station: String) : List<FetchedSchedule> {
        return dao.getScheduleByStation(station)
    }

    private fun insertScheduleFromApiToDb() {
        scheduleApiService.getScheduleBy().enqueue(object: Callback<FetchedSchedule> {
            override fun onResponse(
                call: Call<FetchedSchedule>,
                response: Response<FetchedSchedule>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { scheduleDatabase.queryExecutor.execute {
                        dao.insertSchedule(it)
                    }}
                }
                else {
                    Log.e("API", "Api schedule error")
                }
            }

            override fun onFailure(call: Call<FetchedSchedule>, t: Throwable) {
                Log.e("API", t.message.orEmpty())
            }
        })
    }

    fun getStationsFromApi(callback: ApiCallback<FetchedStations>) {
        scheduleApiService.getAllStations().enqueue(object: Callback<FetchedStations> {
            override fun onResponse(
                call: Call<FetchedStations>,
                response: Response<FetchedStations>
            ) {
                if (response.isSuccessful){
                    callback.onSuccess(response.body())
                    Log.e("API", response.body()!!.countries.joinToString())
                }
                else {
                    callback.onError("API schedule error")
                    Log.e("API", response.body()!!.countries.joinToString())
                }
            }

            override fun onFailure(call: Call<FetchedStations>, t: Throwable) {
                callback.onException(t)
            }

        })
    }

    fun getStationsFromDb() {

    }

    fun getScheduleDirectlyFromApi(station: String) {

    }



    fun getScheduleDirectlyFromApi(callback: ApiCallback<FetchedSchedule>) {
        scheduleApiService.getScheduleBy().enqueue(object: Callback<FetchedSchedule> {
            override fun onResponse(
                call: Call<FetchedSchedule>,
                response: Response<FetchedSchedule>
            ) {
                if (response.isSuccessful){
                    callback.onSuccess(response.body())
                } else {
                    callback.onError("Api schedule error")
                }
            }

            override fun onFailure(call: Call<FetchedSchedule>, t: Throwable) {
                callback.onException(t)
            }
        })
    }
}