package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.SegmentScheduleEntity

data class ScheduleBetweenDTO(
    val pagination: PaginationDTO,
    val search: SearchDTO,
    val segments: List<SegmentDTO>
) {
    fun toScheduleEntity(): List<SegmentScheduleEntity> {
        return segments.map { it.toSegmentEntity(search.date) }
    }
}