package com.vpr.scheduleapp.data.api.dto.stations

import com.vpr.scheduleapp.data.database.stations.entity.SettlementEntity
import com.vpr.scheduleapp.domain.model.stations.Settlement

data class SettlementDTO(
    val codes: CodesDTO,
    val stations: List<StationDTO>,
    val title: String
) {
    fun toSettlement(): Settlement {
        return Settlement(
            code = codes.yandex_code,
            stations = stations.map { it.toStation() },
            title = title
        )
    }

    fun toSettlementEntity(regionCode: String): SettlementEntity {
        return SettlementEntity(
            code = codes.yandex_code,
            stations = stations.map { it.toStationEntity(codes.yandex_code) },
            title = title,
            region_id = regionCode
        )
    }
}