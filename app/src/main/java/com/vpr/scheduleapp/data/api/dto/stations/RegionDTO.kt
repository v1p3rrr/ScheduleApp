package com.vpr.scheduleapp.data.api.dto.stations

import com.vpr.scheduleapp.data.database.stations.entity.RegionEntity
import com.vpr.scheduleapp.domain.model.stations.Region

data class RegionDTO(
    val codes: CodesDTO,
    val settlements: List<SettlementDTO>,
    val title: String
) {
    fun toRegion(): Region {
        return Region(
            code = codes.yandex_code,
            settlements = settlements.map { it.toSettlement() },
            title = title
        )
    }

    fun toRegionEntity(countryCode: String): RegionEntity {
        return RegionEntity(
            code = codes.yandex_code,
            settlements = settlements.map { it.toSettlementEntity(codes.yandex_code) },
            title = title,
            country_id = countryCode
        )
    }
}