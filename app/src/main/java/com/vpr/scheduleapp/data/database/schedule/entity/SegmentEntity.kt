package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Segment

@Entity(tableName = "segment")
data class SegmentEntity(
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
    @ColumnInfo
    val from: StationEntity,
    @ColumnInfo
    val to: StationEntity,
    @ColumnInfo
    val has_transfers: Boolean,
    @ColumnInfo
    val start_date: String,
    @ColumnInfo
    val stops: String,
    @ColumnInfo
    val thread: ThreadEntity
) {
    fun toSegment(): Segment {
        return Segment(
            arrival = arrival,
            arrival_platform = arrival_platform,
            arrival_terminal = arrival_terminal,
            departure = departure,
            departure_platform = departure_platform,
            departure_terminal = departure_terminal,
            duration = duration,
            from = from.toStation(),
            to = to.toStation(),
            has_transfers = has_transfers,
            start_date = start_date,
            stops = stops,
            thread = thread.toThreadd(),
        )
    }
}