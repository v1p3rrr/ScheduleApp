package com.vpr.scheduleapp.data.api.dto.schedule_between

data class SearchDTO(
    val date: String,
    val from: FromXDTO,
    val to: ToXDTO
)