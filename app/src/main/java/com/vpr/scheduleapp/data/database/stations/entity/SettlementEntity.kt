package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "settlement", foreignKeys = [
    ForeignKey(entity = RegionEntity::class, parentColumns = arrayOf("code"), childColumns = arrayOf("region_id")),
])
data class SettlementEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val stations: List<StationEntity>,
    @ColumnInfo
    val title: String,
    @ColumnInfo(index = true)
    val region_id: String
)