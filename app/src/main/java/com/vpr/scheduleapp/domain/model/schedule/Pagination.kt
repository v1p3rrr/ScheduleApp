package com.vpr.scheduleapp.domain.model.schedule

data class Pagination(
    val limit: Int,
    val offset: Int,
    val total: Int
)