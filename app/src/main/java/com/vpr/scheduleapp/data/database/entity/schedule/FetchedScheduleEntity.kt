package com.vpr.scheduleapp.data.database.entity.schedule

import androidx.room.*
import com.vpr.scheduleapp.data.database.converters.*
import com.vpr.scheduleapp.domain.model.schedule.*

@Entity(tableName = "fetched_schedule", primaryKeys = ["date", "station"])
@TypeConverters(ScheduleConverter::class, DirectionConverter::class, Converters::class)
data class FetchedScheduleEntity(
    @ColumnInfo
    val date: String,
    @ColumnInfo
    val directions: List<DirectionEntity>,
    @ColumnInfo
    val interval_schedule: List<ScheduleEntity>,
    @ColumnInfo
    val pagination: PaginationEntity,
    @ColumnInfo
    val schedule: List<ScheduleEntity>,
    @ColumnInfo
    val schedule_direction: ScheduleDirectionEntity,
    @ColumnInfo
    val station: StationEntity
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
}

data class FetchedScheduleWithSchedule(
    @Embedded
    val fetchedSchedule: FetchedScheduleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "fetchedScheduleId",
        entity = Schedule::class
    )
    val schedule: List<Schedule>?
)

@Entity(tableName = "direction")
data class DirectionEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val title: String
) {
    fun toDirections(): Directions {
        return Directions(
            code = code,
            title = title
        )
    }
}

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo
    val arrival: String,
    @ColumnInfo
    val days: String,
    @ColumnInfo
    val departure: String,
    @ColumnInfo
    val travel_time: String,
    @ColumnInfo
    val direction: String,
    @ColumnInfo
    val except_days: String?,
    @ColumnInfo
    val is_fuzzy: Boolean,
    @ColumnInfo
    val platform: String?,
    @ColumnInfo
    val stops: String?,
    @ColumnInfo
    val terminal: String?,
    @ColumnInfo
    val thread: ThreaddEntity
) {
    fun toSchedule(): Schedule {
        return Schedule(
            arrival = arrival,
            days = days,
            departure = departure,
            direction = direction,
            travel_time = travel_time,
            except_days = except_days,
            is_fuzzy = is_fuzzy,
            platform = platform,
            stops = stops,
            terminal = terminal,
            thread = thread.toThreadd()
        )
    }
}

@Entity(tableName = "schedule_direction")
data class ScheduleDirectionEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val title: String
) {
    fun toScheduleDirection(): ScheduleDirection {
        return ScheduleDirection(
            code = code,
            title = title
        )
    }
}

@Entity(tableName = "station")
data class StationEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val popular_title: String?,
    @ColumnInfo
    val short_title: String?,
    @ColumnInfo
    val station_type: String?,
    @ColumnInfo
    val station_type_name: String?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val transport_type: String?,
    @ColumnInfo
    val type: String?
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
}

@Entity(tableName = "thread")
data class ThreaddEntity(
    @PrimaryKey
    val uid: String,
    @ColumnInfo
    val express_type: String?,
    @ColumnInfo
    val number: String?,
    @ColumnInfo
    val short_title: String?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val transport_subtype: TransportSubtypeEntity?,
    @ColumnInfo
    val transport_type: String?,
    @ColumnInfo
    val vehicle: String?
) {
    fun toThreadd(): Threadd {
        return Threadd(
            uid = uid,
            express_type = express_type,
            number = number,
            short_title = short_title,
            title = title,
            transport_subtype = transport_subtype?.toTransportSubtype(),
            transport_type = transport_type,
            vehicle = vehicle,
        )
    }
}

@Entity(tableName = "transport_subtype")
data class TransportSubtypeEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val color: String,
    @ColumnInfo
    val title: String
) {
    fun toTransportSubtype(): TransportSubtype {
        return TransportSubtype(
            code = code,
            color = color,
            title = title
        )
    }
}

@Entity(tableName = "pagination")
data class PaginationEntity(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo
    val limit: Int,
    @ColumnInfo
    val offset: Int,
    @ColumnInfo
    val total: Int
) {
    fun toPagination(): Pagination {
        return Pagination(
            limit = limit,
            offset = offset,
            total = total
        )
    }
}