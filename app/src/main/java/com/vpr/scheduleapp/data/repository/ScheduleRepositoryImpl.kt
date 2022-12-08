package com.vpr.scheduleapp.data.repository

import com.vpr.scheduleapp.data.api.ScheduleApiService
import com.vpr.scheduleapp.data.api.dto.schedule_between.ScheduleBetweenDTO
import com.vpr.scheduleapp.data.api.dto.station_schedule.StationScheduleDTO
import com.vpr.scheduleapp.data.database.schedule.ScheduleDatabase
import com.vpr.scheduleapp.domain.model.schedule.Segment
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
        from: String,
        to: String,
        date: String
    ): Flow<Resource<List<Segment?>>> = flow() {

        emit(Resource.Loading())

        val schedule = dao.getScheduleByStation(from, to, date)
        emit(Resource.Loading(schedule.map { it?.toSegment() } ))
        val scope = CoroutineScope(Dispatchers.IO)
        api.getScheduleByStationCodeAndDate(from_code = from, to_code = to, date = date)
            .enqueue(object : Callback<ScheduleBetweenDTO> {
                override fun onResponse(
                    call: Call<ScheduleBetweenDTO>,
                    response: Response<ScheduleBetweenDTO>
                ) {
                    if (response.isSuccessful && response.body()?.segment!=null) {
                        scope.launch {
                            dao.deleteScheduleByStation(from, to, date)
                            response.body()?.let {
                                dao.insertPagination(it.pagination.toPaginationEntity())
                                it.segment.forEach { segment ->
                                    dao.insertThread(segment.thread.toThreadEntity())
                                    dao.insertStation(segment.from.toStationEntity())
                                    dao.insertStation(segment.to.toStationEntity())
                                    dao.insertSchedule(segment.toSegmentEntity(it.search.date)) }
                            }
                        }
                    } else {
                            emit(Resource.Error(message = "Something went wrong"))
                    }
                }

                override fun onFailure(call: Call<ScheduleBetweenDTO>, t: Throwable) {
                    scope.launch {
                        emit(Resource.Error(message = "Connection problems"))
                    }
                }
            })
        val newSchedule = dao.getScheduleByStation(from, to, date)
        emit(Resource.Success(newSchedule.map { it?.toSegment() }))
    }

    override fun getScheduleFromDb(from: String, to: String, date: String): List<Segment?> {
        return dao.getScheduleByStation(from, to, date).map { it?.toSegment() }
    }

}