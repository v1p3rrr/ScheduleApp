package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.TransportSubtypeEntity

data class TransportSubtypeDTO(
    val code: String,
    val color: String,
    val title: String
) {
    fun toTransportSubtypeEntity(): TransportSubtypeEntity {
        return TransportSubtypeEntity(
            code = code,
            color = color,
            subtype_title = title
        )
    }
}