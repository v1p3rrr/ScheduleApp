package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Segment

@Entity(tableName = "segment_schedule", foreignKeys = [
    ForeignKey(entity = StationEntity::class, parentColumns = arrayOf("code"), childColumns = arrayOf("from_id")),
    ForeignKey(entity = StationEntity::class, parentColumns = arrayOf("code"), childColumns = arrayOf("to_id")),
    ForeignKey(entity = ThreadEntity::class, parentColumns = arrayOf("uid"), childColumns = arrayOf("thread_id")),
    ForeignKey(entity = PaginationEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("pagination_id"), onDelete = ForeignKey.CASCADE)
])
data class SegmentScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo
    val arrival: String,
    @ColumnInfo
    val arrival_platform: String,
    @ColumnInfo
    val arrival_terminal: String,
    @ColumnInfo
    val departure: String,
    @ColumnInfo
    val departure_platform: String,
    @ColumnInfo
    val departure_terminal: String,
    @ColumnInfo
    val duration: Int,
    @ColumnInfo(index = true)
    val from_id: String,
    @ColumnInfo(index = true)
    val to_id: String,
    @ColumnInfo
    val has_transfers: Boolean,
    @ColumnInfo
    val start_date: String,
    @ColumnInfo
    val stops: String,
    @ColumnInfo(index = true)
    val thread_id: String,
    @ColumnInfo(index = true)
    val pagination_id: String? = null,
    @ColumnInfo
    val date: String
)