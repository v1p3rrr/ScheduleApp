package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.ScheduleBetweenEntity

data class ScheduleBetweenDTO(
    val pagination: PaginationDTO,
    val search: SearchDTO,
    val segment: SegmentDTO
) {
    fun toScheduleBetweenEntity(): ScheduleBetweenEntity {
        return ScheduleBetweenEntity(
            pagination = pagination.toPaginationEntity(),
            segment = segment.toSegmentEntity(),
        )
    }
}