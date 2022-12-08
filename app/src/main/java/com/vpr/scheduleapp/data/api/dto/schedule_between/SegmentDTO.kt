package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.SegmentScheduleEntity

data class SegmentDTO(
    val arrival: String,
    val arrival_platform: String,
    val arrival_terminal: String,
    val departure: String,
    val departure_platform: String,
    val departure_terminal: String,
    val duration: Int,
    val from: FromDTO,
    val to: ToDTO,
    val has_transfers: Boolean,
    val start_date: String,
    val stops: String,
    val thread: ThreadDTO,
    val tickets_info: TicketsInfoDTO
) {
    fun toSegmentEntity(date: String): SegmentScheduleEntity {
        return SegmentScheduleEntity(
            arrival = arrival,
            arrival_platform = arrival_platform,
            arrival_terminal = arrival_terminal,
            departure = departure,
            departure_platform = departure_platform,
            departure_terminal = departure_terminal,
            duration = duration,
            from_id = from.code,
            to_id = to.code,
            has_transfers = has_transfers,
            start_date = start_date,
            stops = stops,
            thread_id = thread.uid,
            date = date
        )
    }
}