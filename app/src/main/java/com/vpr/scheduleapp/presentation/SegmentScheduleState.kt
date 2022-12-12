package com.vpr.scheduleapp.presentation

import com.vpr.scheduleapp.domain.model.schedule.Segment

data class SegmentScheduleState(
    val segment: List<Segment?> = emptyList(),
    val isLoading: Boolean = false
)
