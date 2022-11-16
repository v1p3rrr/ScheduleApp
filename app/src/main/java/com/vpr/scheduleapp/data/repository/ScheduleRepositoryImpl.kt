package com.vpr.scheduleapp.data.repository

import android.util.Log
import com.vpr.scheduleapp.data.remote.ApiCallback
import com.vpr.scheduleapp.data.remote.ScheduleApiService
import com.vpr.scheduleapp.data.database.ScheduleDatabase
import com.vpr.scheduleapp.data.remote.dto.schedule.FetchedScheduleDTO
import com.vpr.scheduleapp.data.remote.dto.stations.CountryDTO
import com.vpr.scheduleapp.domain.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.domain.repository.ScheduleRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepositoryImpl @Inject constructor(private val scheduleDatabase: ScheduleDatabase, private val scheduleApiService: ScheduleApiService): ScheduleRepository {

    private val dao = scheduleDatabase.scheduleDao()

    fun getScheduleByStationCodeAndDate(stationCode: String, date: String) : FetchedSchedule? {
        insertScheduleFromApiToDb()
        return getScheduleFromDb(stationCode, date)
        //todo sequentially
    }

    override fun getScheduleFromDb(stationCode: String, date: String) : FetchedSchedule? {
        return dao.getScheduleByStationCode(stationCode, date)?.toFetchedSchedule()
    }
    private fun insertScheduleFromApiToDb() {
        scheduleApiService.getScheduleBy().enqueue(object: Callback<FetchedScheduleDTO> {
            override fun onResponse(
                call: Call<FetchedScheduleDTO>,
                response: Response<FetchedScheduleDTO>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { scheduleDatabase.queryExecutor.execute {
                        dao.insertSchedule(it.toFetchedScheduleEntity())
                    }}
                }
                else {
                    Log.e("API", "Api scheduleDTO error")
                }
            }

            override fun onFailure(call: Call<FetchedScheduleDTO>, t: Throwable) {
                Log.e("API", t.message.orEmpty())
            }
        })
    }

    fun getStationsFromApi(callback: ApiCallback<List<CountryDTO>>) {
        scheduleApiService.getAllStations().enqueue(object: Callback<List<CountryDTO>> {
            override fun onResponse(
                call: Call<List<CountryDTO>>,
                response: Response<List<CountryDTO>>
            ) {
                if (response.isSuccessful){
                    callback.onSuccess(response.body())
                    Log.e("API", response.body()!!.joinToString())
                }
                else {
                    callback.onError("API scheduleDTO error")
                    Log.e("API", response.body()!!.joinToString())
                }
            }

            override fun onFailure(call: Call<List<CountryDTO>>, t: Throwable) {
                callback.onException(t)
            }

        })
    }

    fun getStationsFromDb() {

    }

    fun getScheduleDirectlyFromApi(station: String) {

    }



    fun getScheduleDirectlyFromApi(callback: ApiCallback<FetchedSchedule>) {
        scheduleApiService.getScheduleBy().enqueue(object: Callback<FetchedScheduleDTO> {
            override fun onResponse(
                call: Call<FetchedScheduleDTO>,
                response: Response<FetchedScheduleDTO>
            ) {
                if (response.isSuccessful){
                    callback.onSuccess(response.body()?.toFetchedSchedule())
                } else {
                    callback.onError("Api scheduleDTO error")
                }
            }

            override fun onFailure(call: Call<FetchedScheduleDTO>, t: Throwable) {
                callback.onException(t)
            }
        })
        insertScheduleFromApiToDb()
    }

}