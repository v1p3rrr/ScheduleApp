package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Station


@Entity(tableName = "station")
data class StationEntity(
    @PrimaryKey
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