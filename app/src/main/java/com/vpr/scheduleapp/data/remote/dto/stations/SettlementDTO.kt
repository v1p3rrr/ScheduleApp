package com.vpr.scheduleapp.data.remote.dto.stations

import com.vpr.scheduleapp.data.database.entity.stations.Settlement

data class SettlementDTO(
    val codes: CodesDTO,
    val stations: List<StationDTO>,
    val title: String
) {
    fun toSettlement(): Settlement {
        return Settlement(
            codes = codes.toCodes(),
            stations = stations.map { it.toStation() },
            title = title
        )
    }
}