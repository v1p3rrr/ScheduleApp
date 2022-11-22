package com.vpr.scheduleapp.data.api.dto.station_schedule

import com.vpr.scheduleapp.data.database.schedule.entity.StationScheduleEntity
import com.vpr.scheduleapp.domain.model.schedule.StationSchedule

data class StationScheduleDTO(
    val date: String,
    val directions: List<DirectionsDTO>,
    val interval_schedule: List<ScheduleDTO>,
    val pagination: PaginationDTO,
    val schedule: List<ScheduleDTO>,
    val schedule_direction: ScheduleDirectionDTO,
    val station: StationDTO
) {
    fun toStationSchedule(): StationSchedule {
        return StationSchedule(
            date = date,
            directions = directions.map { it.toDirections() },
            interval_schedule = interval_schedule.map { it.toSchedule() },
            pagination = pagination.toPagination(),
            schedule = schedule.map { it.toSchedule() },
            schedule_direction = schedule_direction.toScheduleDirection(),
            station = station.toStation(),
        )
    }

    fun toStationScheduleEntity(): StationScheduleEntity {
        return StationScheduleEntity(
            date = date,
            directions = directions.map { it.toDirectionsEntity() },
            interval_schedule = interval_schedule.map { it.toScheduleEntity() },
            pagination = pagination.toPaginationEntity(),
            schedule = schedule.map { it.toScheduleEntity() },
            schedule_direction = schedule_direction.toScheduleDirectionEntity(),
            station = station.toStationEntity(),
        )
    }
}

















