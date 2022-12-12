package com.vpr.scheduleapp.data.repository

import android.util.Log
import com.vpr.scheduleapp.data.api.ScheduleApiService
import com.vpr.scheduleapp.data.database.schedule.ScheduleDatabase
import com.vpr.scheduleapp.domain.model.schedule.Segment
import com.vpr.scheduleapp.domain.repository.ScheduleRepository
import com.vpr.scheduleapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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

        val localSchedule =
            dao.getScheduleByStation(from = from, to = to, date = date).map { it?.toSegment() }
        emit(Resource.Loading(localSchedule))

        try {
            val scheduleFromApi =
                api.getScheduleByStationCodeAndDate(from_code = from, to_code = to, date = date)
            dao.deleteScheduleByStation(from, to, date)
            dao.insertPagination(scheduleFromApi.pagination.toPaginationEntity())
            scheduleFromApi.segments.forEach { segment ->
                dao.insertThread(segment.thread.toThreadEntity())
                dao.insertStation(segment.from.toStationEntity())
                dao.insertStation(segment.to.toStationEntity())
                dao.insertSchedule(segment.toSegmentEntity(scheduleFromApi.search.date))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = localSchedule
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check your internet connection",
                    data = localSchedule
                )
            )
        } catch (e: NullPointerException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = localSchedule
                )
            )
            Log.e("Error", e.stackTraceToString())
        }

        val newSchedule = dao.getScheduleByStation(from, to, date)
        emit(Resource.Success(newSchedule.map { it?.toSegment() }))
    }

    override fun getScheduleFromDb(
        from: String,
        to: String,
        date: String
    ): Flow<Resource<List<Segment?>>> = flow() {
        emit(Resource.Loading())

        val localSchedule = dao.getScheduleByStation(from, to, date).map { it?.toSegment() }
        emit(Resource.Loading(localSchedule.map { it }))
    }

}