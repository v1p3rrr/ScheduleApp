package com.vpr.scheduleapp.data.api.dto.schedule_between

import com.vpr.scheduleapp.data.database.schedule.entity.ThreadEntity

data class ThreadDTO(
    val carrier: CarrierDTO,
    val express_type: String,
    val number: String,
    val short_title: String,
    val thread_method_link: String,
    val title: String,
    val transport_subtype: TransportSubtypeDTO,
    val transport_type: String,
    val uid: String,
    val vehicle: String
) {
    fun toThreadEntity(): ThreadEntity {
        return ThreadEntity(
            express_type = express_type,
            number = number,
            short_title = short_title,
            title = title,
            transport_subtype = transport_subtype.toTransportSubtypeEntity(),
            transport_type = transport_type,
            uid = uid,
            vehicle = vehicle
        )
    }
}