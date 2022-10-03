package com.vpr.scheduleapp.data.repository

import com.vpr.scheduleapp.data.api.ApiCallback
import com.vpr.scheduleapp.data.api.RetrofitBuilder
import com.vpr.scheduleapp.data.api.ScheduleApiService
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleRepository() {

    val scheduleApiService = RetrofitBuilder.getRetrofit().create(ScheduleApiService::class.java)

    fun getSchedule(callback: ApiCallback<FetchedSchedule>) {
        scheduleApiService.getScheduleBy().enqueue(object: Callback<FetchedSchedule> {
            override fun onResponse(
                call: Call<FetchedSchedule>,
                response: Response<FetchedSchedule>
            ) {
                if (response.isSuccessful){
                    callback.onSuccess(response.body())
                }
                else {
                    callback.onError("Api schedule error")
                }
            }

            override fun onFailure(call: Call<FetchedSchedule>, t: Throwable) {
                callback.onException(t)
            }

        })

    }

    fun getStationsFromApi() {

    }

    fun getStationsFromDb() {

    }

    fun getScheduleFromApi(station: String) {

    }

    fun getScheduleFromDb(station: String) {

    }
}