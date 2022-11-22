package com.vpr.scheduleapp.data.api.dto.station_schedule

import android.icu.text.SimpleDateFormat
import com.vpr.scheduleapp.data.database.schedule.entity.ScheduleEntity
import com.vpr.scheduleapp.domain.model.schedule.Schedule
import java.util.*

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
    val thread: ThreadDTO
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