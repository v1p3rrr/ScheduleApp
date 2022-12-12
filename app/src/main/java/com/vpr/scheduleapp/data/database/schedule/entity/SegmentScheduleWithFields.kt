package com.vpr.scheduleapp.data.database.schedule.entity

import android.icu.text.SimpleDateFormat
import android.text.format.DateUtils
import androidx.room.Embedded
import androidx.room.Relation
import com.vpr.scheduleapp.domain.model.schedule.Segment
import java.util.*

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
    val pagination: PaginationEntity?,
) {
    fun toSegment(): Segment {
        return Segment(
            arrival = convertTime(segmentSchedule.arrival),
            arrival_platform = segmentSchedule.arrival_platform,
            arrival_terminal = segmentSchedule.arrival_terminal,
            departure = convertTime(segmentSchedule.departure),
            departure_platform = segmentSchedule.departure_platform,
            departure_terminal = segmentSchedule.departure_terminal,
            duration = secsToTime(segmentSchedule.duration),
            from = from.toStation(),
            to = to.toStation(),
            has_transfers = segmentSchedule.has_transfers,
            start_date = segmentSchedule.start_date,
            stops = segmentSchedule.stops,
            thread = thread.toThreadd(),
            pagination = pagination?.toPagination(),
            date = segmentSchedule.date
        )
    }
}

private fun convertTime(time: String): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale("ru", "RU"))
    val newFormat = SimpleDateFormat("HH:mm", Locale("ru", "RU"))
    val parsedTime: Date = initialFormat.parse(time)
    return newFormat.format(parsedTime)
}

private fun secsToTime(totalSecs: Int?): String {
    if (totalSecs == null) return "неизвестно"
    val hours = totalSecs / 3600
    val minutes = (totalSecs % 3600) / 60
    val seconds = totalSecs % 60
    return ("В пути $hours ч. $minutes м.")
}