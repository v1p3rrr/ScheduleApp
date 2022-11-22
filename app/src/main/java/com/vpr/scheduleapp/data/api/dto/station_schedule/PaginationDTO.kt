package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.PaginationEntity
import com.vpr.scheduleapp.domain.model.schedule.Pagination

data class PaginationDTO(
    val limit: Int,
    val offset: Int,
    val total: Int
) {
    fun toPagination(): Pagination {
        return Pagination(
            limit = limit,
            offset = offset,
            total = total
        )
    }

    fun toPaginationEntity(): PaginationEntity {
        return PaginationEntity(
            limit = limit,
            offset = offset,
            total = total
        )
    }
}