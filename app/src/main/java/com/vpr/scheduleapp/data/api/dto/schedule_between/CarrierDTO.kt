package com.vpr.scheduleapp.data.api.dto.schedule_between

data class CarrierDTO(
    val address: String,
    val code: Int,
    val codes: CodesDTO,
    val contacts: String,
    val email: String,
    val logo: String,
    val logo_svg: String,
    val phone: String,
    val title: String,
    val url: String
)