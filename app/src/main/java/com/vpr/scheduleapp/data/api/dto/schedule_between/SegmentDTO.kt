package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.SegmentEntity

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
    fun toSegmentEntity(): SegmentEntity {
        return SegmentEntity(
            arrival = arrival,
            arrival_platform = arrival_platform,
            arrival_terminal = arrival_terminal,
            departure = departure,
            departure_platform = departure_platform,
            departure_terminal = departure_terminal,
            duration = duration,
            from = from.toStationEntity(),
            to = to.toStationEntity(),
            has_transfers = has_transfers,
            start_date = start_date,
            stops = stops,
            thread = thread.toThreadEntity()
        )
    }
}