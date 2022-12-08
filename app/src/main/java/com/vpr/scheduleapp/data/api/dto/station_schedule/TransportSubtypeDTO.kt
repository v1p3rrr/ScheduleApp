package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.TransportSubtypeEntity
import com.vpr.scheduleapp.domain.model.schedule.TransportSubtype

data class TransportSubtypeDTO(
    val code: String,
    val color: String,
    val title: String
) {
    fun toTransportSubtype(): TransportSubtype {
        return TransportSubtype(
            code = code,
            color = color,
            title = title
        )
    }

    fun toTransportSubtypeEntity(): TransportSubtypeEntity {
        return TransportSubtypeEntity(
            code = code,
            color = color,
            subtype_title = title
        )
    }
}