package com.vpr.scheduleapp.data.remote.dto.schedule

import android.icu.text.SimpleDateFormat
import com.vpr.scheduleapp.data.database.entity.schedule.*
import com.vpr.scheduleapp.data.database.entity.schedule.FetchedScheduleEntity
import com.vpr.scheduleapp.domain.model.schedule.*
import java.util.*

data class FetchedScheduleDTO(
    val date: String,
    val directions: List<DirectionsDTO>,
    val interval_schedule: List<ScheduleDTO>,
    val pagination: PaginationDTO,
    val schedule: List<ScheduleDTO>,
    val schedule_direction: ScheduleDirectionDTO,
    val station: StationDTO
) {
    fun toFetchedSchedule(): FetchedSchedule {
        return FetchedSchedule(
            date = date,
            directions = directions.map { it.toDirections() },
            interval_schedule = interval_schedule.map { it.toSchedule() },
            pagination = pagination.toPagination(),
            schedule = schedule.map { it.toSchedule() },
            schedule_direction = schedule_direction.toScheduleDirection(),
            station = station.toStation(),
        )
    }

    fun toFetchedScheduleEntity(): FetchedScheduleEntity {
        return FetchedScheduleEntity(
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

data class DirectionsDTO(
    val code: String,
    val title: String
) {
    fun toDirections(): Directions {
        return Directions(
            code = code,
            title = title
        )
    }

    fun toDirectionsEntity(): DirectionEntity {
        return DirectionEntity(
            code = code,
            title = title
        )
    }
}

data class ScheduleDTO(
    var arrival: String,
    val days: String,
    var departure: String,
    val direction: String,
    val except_days: String,
    val is_fuzzy: Boolean,
    val platform: String,
    val stops: String,
    val terminal: String,
    val thread: ThreaddDTO
) {
    fun toSchedule(): Schedule {
        return Schedule(
            arrival = convertTime(arrival),
            days = days,
            departure = convertTime(departure),
            travel_time = calculateTimeDifference(departure, arrival),
            direction = direction,
            except_days = except_days,
            is_fuzzy = is_fuzzy,
            platform = platform,
            stops = stops,
            terminal = terminal,
            thread = thread.toThreadd()
        )
    }

    fun toScheduleEntity(): ScheduleEntity {
        return ScheduleEntity(
            arrival = arrival,
            days = days,
            departure = departure,
            travel_time = calculateTimeDifference(departure, arrival),
            direction = direction,
            except_days = except_days,
            is_fuzzy = is_fuzzy,
            platform = platform,
            stops = stops,
            terminal = terminal,
            thread = thread.toThreaddEntity()
        )
    }
}

data class ScheduleDirectionDTO(
    val code: String,
    val title: String
) {
    fun toScheduleDirection(): ScheduleDirection {
        return ScheduleDirection(
            code = code,
            title = title
        )
    }

    fun toScheduleDirectionEntity(): ScheduleDirectionEntity {
        return ScheduleDirectionEntity(
            code = code,
            title = title
        )
    }
}

data class StationDTO(
    val code: String,
    val popular_title: String,
    val short_title: String,
    val station_type: String,
    val station_type_name: String,
    val title: String,
    val transport_type: String,
    val type: String
) {
    fun toStation(): Station {
        return Station(
            code = code,
            popular_title = popular_title,
            short_title = short_title,
            station_type = station_type,
            station_type_name = station_type_name,
            title = title,
            transport_type = transport_type,
            type = type
        )
    }

    fun toStationEntity(): StationEntity {
        return StationEntity(
            code = code,
            popular_title = popular_title,
            short_title = short_title,
            station_type = station_type,
            station_type_name = station_type_name,
            title = title,
            transport_type = transport_type,
            type = type
        )
    }
}

data class ThreaddDTO(
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

    fun toThreaddEntity(): ThreaddEntity {
        return ThreaddEntity(
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

data class TransportSubtypeDTO(
    val code: String,
    val color: String,
    val title: String
) {
    fun toTransportSubtype(): TransportSubtype {
        return TransportSubtype(
            code = code,
            color = color,
            title = title
        )
    }

    fun toTransportSubtypeEntity(): TransportSubtypeEntity {
        return TransportSubtypeEntity(
            code = code,
            color = color,
            title = title
        )
    }
}

data class CodesDTO(
    val iata: String,
    val icao: String,
    val sirena: String
)

data class CarrierDTO(
    val code: Int,
    val codes: CodesDTO,
    val title: String
)

data class PaginationDTO(
    val limit: Int,
    val offset: Int,
    val total: Int
) {
    fun toPagination(): Pagination {
        return Pagination(
            limit = limit,
            offset = offset,
            total = total
        )
    }

    fun toPaginationEntity(): PaginationEntity {
        return PaginationEntity(
            limit = limit,
            offset = offset,
            total = total
        )
    }
}

private fun calculateTimeDifference(start: String, end: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale("ru", "RU"))
    val parsedStart: Date = dateFormat.parse(start)
    val parsedEnd: Date = dateFormat.parse(end)
    val diff = parsedEnd.time - parsedStart.time
    return "${diff / 3600000}:${(diff % 3600000) / 60000}"
}

private fun convertTime(time: String): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale("ru", "RU"))
    val newFormat = SimpleDateFormat("HH:mm", Locale("ru", "RU"))
    val parsedTime: Date = initialFormat.parse(time)
    return newFormat.format(parsedTime)
}

private fun convertDate(date: String): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru", "RU"))
    val newFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru", "RU"))
    val parsedDate: Date = initialFormat.parse(date)
    return newFormat.format(parsedDate)
}