package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.DirectionEntity
import com.vpr.scheduleapp.domain.model.schedule.Directions

data class DirectionsDTO(
    val code: String,
    val title: String
) {
    fun toDirections(): Directions {
        return Directions(
            code = code,
            title = title
        )
    }

    fun toDirectionsEntity(): DirectionEntity {
        return DirectionEntity(
            code = code,
            title = title
        )
    }
}
