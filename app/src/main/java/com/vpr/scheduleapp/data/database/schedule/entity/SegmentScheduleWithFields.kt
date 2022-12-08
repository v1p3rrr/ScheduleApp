package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.vpr.scheduleapp.domain.model.schedule.Segment

data class SegmentScheduleWithFields(
    @Embedded
    val segmentSchedule: SegmentScheduleEntity,
    @Relation(
        parentColumn = "from_id",
        entityColumn = "code",
        entity = StationEntity::class
    )
    val from: StationEntity,
    @Relation(
        parentColumn = "to_id",
        entityColumn = "code",
        entity = StationEntity::class
    )
    val to: StationEntity,
    @Relation(
        parentColumn = "thread_id",
        entityColumn = "uid",
        entity = ThreadEntity::class
    )
    val thread: ThreadEntity,
    @Relation(
        parentColumn = "pagination_id",
        entityColumn = "id",
        entity = PaginationEntity::class
    )
    val pagination: PaginationEntity,
) {
    fun toSegment(): Segment {
        return Segment(
            arrival = segmentSchedule.arrival,
            arrival_platform = segmentSchedule.arrival_platform,
            arrival_terminal = segmentSchedule.arrival_terminal,
            departure = segmentSchedule.departure,
            departure_platform = segmentSchedule.departure_platform,
            departure_terminal = segmentSchedule.departure_terminal,
            duration = segmentSchedule.duration,
            from = from.toStation(),
            to = to.toStation(),
            has_transfers = segmentSchedule.has_transfers,
            start_date = segmentSchedule.start_date,
            stops = segmentSchedule.stops,
            thread = thread.toThreadd(),
            pagination = pagination.toPagination(),
            date = segmentSchedule.date
        )
    }
}