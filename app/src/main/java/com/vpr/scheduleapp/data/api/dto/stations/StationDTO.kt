package com.vpr.scheduleapp.data.api.dto.stations

import com.vpr.scheduleapp.data.database.stations.entity.StationEntity
import com.vpr.scheduleapp.domain.model.stations.Station

data class StationDTO(
    val codes: CodesDTO,
    val direction: String,
    val latitude: Double?,
    val longitude: Double?,
    val station_type: String,
    val title: String,
    val transport_type: String
) {
    fun toStation(): Station {
        return Station(
            code = codes.yandex_code,
            direction = direction,
            latitude = latitude,
            longitude = longitude,
            station_type = station_type,
            title = title,
            transport_type = transport_type
        )
    }

    fun toStationEntity(): StationEntity {
        return StationEntity(
            code = codes.yandex_code,
            direction = direction,
            latitude = latitude,
            longitude = longitude,
            station_type = station_type,
            title = title,
            transport_type = transport_type
        )
    }
}