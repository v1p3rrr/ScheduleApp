package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.PaginationEntity

data class PaginationDTO(
    val limit: Int,
    val offset: Int,
    val total: Int
) {
    fun toPaginationEntity(): PaginationEntity {
        return PaginationEntity(
            limit = limit,
            offset = offset,
            total = total
        )
    }
}