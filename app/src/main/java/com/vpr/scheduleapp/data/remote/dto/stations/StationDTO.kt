package com.vpr.scheduleapp.data.remote.dto.stations

import com.vpr.scheduleapp.data.database.entity.stations.Station

data class StationDTO(
    val codes: CodesXXXDTO,
    val direction: String,
    val latitude: String,
    val longitude: String,
    val station_type: String,
    val title: String,
    val transport_type: String
) {
    fun toStation(): Station {
        return Station(
            codes = codes.toCodesXXX(),
            direction = direction,
            latitude = latitude,
            longitude = longitude,
            station_type = station_type,
            title = title,
            transport_type = transport_type
        )
    }
}