package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.StationEntity

data class FromDTO(
    val code: String,
    val popular_title: String,
    val short_title: String,
    val station_type_name: String,
    val title: String,
    val transport_type: String,
    val type: String
) {
    fun toStationEntity(): StationEntity {
        return StationEntity(
            code = code,
            popular_title = popular_title,
            short_title = short_title,
            station_type = "",
            station_type_name = station_type_name,
            title = title,
            transport_type = transport_type,
            type = type
        )
    }
}