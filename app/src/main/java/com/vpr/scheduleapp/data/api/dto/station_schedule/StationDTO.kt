package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.StationEntity
import com.vpr.scheduleapp.domain.model.schedule.Station

data class StationDTO(
    val code: String,
    val popular_title: String,
    val short_title: String,
    val station_type: String,
    val station_type_name: String,
    val title: String,
    val transport_type: String,
    val type: String
) {
    fun toStation(): Station {
        return Station(
            code = code,
            popular_title = popular_title,
            short_title = short_title,
            station_type = station_type,
            station_type_name = station_type_name,
            title = title,
            transport_type = transport_type,
            type = type
        )
    }

    fun toStationEntity(): StationEntity {
        return StationEntity(
            code = code,
            popular_title = popular_title,
            short_title = short_title,
            station_type = station_type,
            station_type_name = station_type_name,
            title = title,
            transport_type = transport_type,
            type = type
        )
    }
}