package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "region")
data class RegionEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val settlements: List<SettlementEntity>,
    @ColumnInfo
    val title: String
)