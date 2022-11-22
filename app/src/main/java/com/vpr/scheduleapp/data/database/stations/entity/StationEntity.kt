package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station")
data class StationEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val direction: String,
    @ColumnInfo
    val latitude: Double?, //double
    @ColumnInfo
    val longitude: Double?, //double
    @ColumnInfo
    val station_type: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val transport_type: String
)