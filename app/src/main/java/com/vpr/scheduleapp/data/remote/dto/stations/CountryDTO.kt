package com.vpr.scheduleapp.data.remote.dto.stations

import com.vpr.scheduleapp.data.database.entity.stations.Country

data class CountryDTO(
    val codes: CodesDTO,
    val regions: List<RegionDTO>,
    val title: String
) {
    fun toCountry(): Country {
        return Country(
            codes = codes.toCodes(),
            regions = regions.map { it.toRegion() },
            title = title
        )
    }
}