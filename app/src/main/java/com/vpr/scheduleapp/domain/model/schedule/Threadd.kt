package com.vpr.scheduleapp.domain.model.schedule

data class Threadd(
    val uid: String?,
    val express_type: String?,
    val number: String?,
    val short_title: String?,
    val title: String?,
    val transport_subtype: TransportSubtype?,
    val transport_type: String?,
    val vehicle: String?
)