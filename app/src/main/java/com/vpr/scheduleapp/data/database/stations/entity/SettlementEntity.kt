package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settlement")
data class SettlementEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val stations: List<StationEntity>,
    @ColumnInfo
    val title: String
)