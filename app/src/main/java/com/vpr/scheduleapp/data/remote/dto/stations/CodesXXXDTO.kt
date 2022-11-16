package com.vpr.scheduleapp.data.remote.dto.stations

import com.vpr.scheduleapp.data.database.entity.stations.CodesXXX

data class CodesXXXDTO(
    val esr_code: String,
    val yandex_code: String
) {
    fun toCodesXXX(): CodesXXX {
        return CodesXXX(
            esr_code = esr_code,
            yandex_code = yandex_code
        )
    }
}