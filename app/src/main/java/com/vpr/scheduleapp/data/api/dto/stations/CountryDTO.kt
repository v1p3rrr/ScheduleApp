package com.vpr.scheduleapp.data.api.dto.stations

import com.vpr.scheduleapp.data.database.stations.entity.CountryEntity
import com.vpr.scheduleapp.domain.model.stations.Country

data class CountryDTO(
    val codes: CodesDTO,
    val regions: List<RegionDTO>,
    val title: String
) {
    fun toCountry(): Country {
        return Country(
            code = codes.yandex_code,
            regions = regions.map { it.toRegion() },
            title = title
        )
    }

    fun toCountryEntity(): CountryEntity {
        return CountryEntity(
            code = codes.yandex_code,
            regions = regions.map { it.toRegionEntity(codes.yandex_code) },
            title = title
        )
    }
}