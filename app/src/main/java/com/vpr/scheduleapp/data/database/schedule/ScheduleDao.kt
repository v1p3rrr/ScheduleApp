package com.vpr.scheduleapp.data.database.schedule

import androidx.room.*
import com.vpr.scheduleapp.data.database.schedule.entity.*

@Dao
abstract class ScheduleDao {

    @Query("SELECT * FROM schedule_station")
    abstract fun getAllSchedule(): List<StationScheduleEntity>

    @Query("SELECT * FROM station WHERE code = :stationCode LIMIT 1")
    abstract fun getStationByCode(stationCode: String): StationEntity?

    //@Query("SELECT * FROM schedule_station WHERE station = :station AND date = :date LIMIT 1")
    //abstract fun getScheduleByStation(station: StationEntity, date: String): StationScheduleEntity?

    @Query("SELECT * FROM segment_schedule WHERE from_id = :from AND to_id = :to AND date = :date")
    abstract fun getScheduleByStation(from: String, to: String, date: String): List<SegmentScheduleWithFields?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSchedule(segmentScheduleEntity: SegmentScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertStation(stationEntity: StationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertThread(threadEntity: ThreadEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPagination(paginationEntity: PaginationEntity)

    @Query("DELETE FROM segment_schedule WHERE from_id = :from AND to_id = :to AND date = :date")
    abstract fun deleteScheduleByStation(from: String, to: String, date: String)

    @Update
    abstract suspend fun update(schedule: StationScheduleEntity)

    @Delete
    abstract suspend fun delete(schedule: StationScheduleEntity)
}