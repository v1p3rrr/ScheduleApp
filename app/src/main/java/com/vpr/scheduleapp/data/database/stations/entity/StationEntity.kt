package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.data.database.schedule.entity.StationEntity

@Entity(tableName = "station", foreignKeys = [
    ForeignKey(entity = SettlementEntity::class, parentColumns = arrayOf("code"), childColumns = arrayOf("settlement_id")),
])
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
    val transport_type: String,
    @ColumnInfo(index = true)
    val settlement_id: String
)