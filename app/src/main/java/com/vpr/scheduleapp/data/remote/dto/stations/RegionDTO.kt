package com.vpr.scheduleapp.data.remote.dto.stations

import com.vpr.scheduleapp.data.database.entity.stations.Region

data class RegionDTO(
    val codes: CodesDTO,
    val settlements: List<SettlementDTO>,
    val title: String
) {
    fun toRegion(): Region {
        return Region(
            codes = codes.toCodes(),
            settlements = settlements.map { it.toSettlement() },
            title = title
        )
    }
}