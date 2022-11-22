package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.ThreadEntity
import com.vpr.scheduleapp.domain.model.schedule.Threadd

data class ThreadDTO(
    val uid: String,
    val carrier: CarrierDTO,
    val express_type: String,
    val number: String,
    val short_title: String,
    val title: String,
    val transport_subtype: TransportSubtypeDTO,
    val transport_type: String,
    val vehicle: String
) {
    fun toThreadd(): Threadd {
        return Threadd(
            uid = uid,
            express_type = express_type,
            number = number,
            short_title = short_title,
            title = title,
            transport_subtype = transport_subtype.toTransportSubtype(),
            transport_type = transport_type,
            vehicle = vehicle,
        )
    }

    fun toThreaddEntity(): ThreadEntity {
        return ThreadEntity(
            uid = uid,
            express_type = express_type,
            number = number,
            short_title = short_title,
            title = title,
            transport_subtype = transport_subtype.toTransportSubtypeEntity(),
            transport_type = transport_type,
            vehicle = vehicle,
        )
    }
}