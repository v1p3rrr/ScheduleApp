package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val regions: List<RegionEntity>,
    @ColumnInfo
    val title: String
)