package com.vpr.scheduleapp.data.remote.dto.stations

import com.vpr.scheduleapp.data.database.entity.stations.Codes

data class CodesDTO(
    val yandex_code: String
) {
    fun toCodes(): Codes {
        return Codes(
            yandex_code = yandex_code
        )
    }
}