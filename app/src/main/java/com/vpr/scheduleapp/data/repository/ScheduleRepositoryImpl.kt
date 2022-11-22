package com.vpr.scheduleapp.data.repository

import com.vpr.scheduleapp.data.api.ScheduleApiService
import com.vpr.scheduleapp.data.api.dto.station_schedule.StationScheduleDTO
import com.vpr.scheduleapp.data.database.schedule.ScheduleDatabase
import com.vpr.scheduleapp.domain.model.schedule.StationSchedule
import com.vpr.scheduleapp.domain.repository.ScheduleRepository
import com.vpr.scheduleapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleDatabase: ScheduleDatabase,
    private val api: ScheduleApiService
) : ScheduleRepository {

    private val dao = scheduleDatabase.scheduleDao()

    override fun getScheduleByStationCodeAndDate(
        stationCode: String,
        date: String
    ): Flow<Resource<StationSchedule>> = flow {

        emit(Resource.Loading())

        val schedule = dao.getScheduleByStationCode(stationCode, date)
        emit(Resource.Loading(schedule?.toStationSchedule()))
        val scope = CoroutineScope(Dispatchers.IO)
        api.getScheduleOnStation(station_code = stationCode, date = date)
            .enqueue(object : Callback<StationScheduleDTO> {
                override fun onResponse(
                    call: Call<StationScheduleDTO>,
                    response: Response<StationScheduleDTO>
                ) {
                    if (response.isSuccessful) {
                        scope.launch {
                            dao.deleteSchedule(stationCode, date)
                            response.body()?.toStationScheduleEntity()
                                ?.let { dao.insertSchedule(it) }
                        }
                    } else {
                        scope.launch {
                            emit(Resource.Error(message = "Something went wrong"))
                        }
                    }
                }

                override fun onFailure(call: Call<StationScheduleDTO>, t: Throwable) {
                    scope.launch {
                        emit(Resource.Error(message = "Connection problems"))
                    }
                }
            })
        val newSchedule = dao.getScheduleByStationCode(stationCode, date)
        emit(Resource.Success(newSchedule?.toStationSchedule()))

        val newSchedule1 = dao.getAllSchedule()
        emit(Resource.Success(newSchedule1[0].toStationSchedule()))
    }

    override fun getScheduleFromDb(stationCode: String, date: String): StationSchedule? {
        return dao.getScheduleByStationCode(stationCode, date)?.toStationSchedule()
    }

}