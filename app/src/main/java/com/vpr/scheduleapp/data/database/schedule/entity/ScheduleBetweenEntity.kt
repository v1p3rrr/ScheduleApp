package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.ScheduleBetween

@Entity(tableName = "schedule_between")
data class ScheduleBetweenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo
    val pagination: PaginationEntity,
    @ColumnInfo
    val segment: SegmentEntity
) {
    fun toScheduleBetween(): ScheduleBetween {
        return ScheduleBetween(
            pagination = pagination.toPagination(),
            segment = segment.toSegment(),
        )
    }
}