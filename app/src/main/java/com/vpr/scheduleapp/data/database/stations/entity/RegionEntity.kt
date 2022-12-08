package com.vpr.scheduleapp.data.database.stations.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "region", foreignKeys = [
    ForeignKey(entity = CountryEntity::class, parentColumns = arrayOf("code"), childColumns = arrayOf("country_id")),
])
data class RegionEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val settlements: List<SettlementEntity>,
    @ColumnInfo
    val title: String,
    @ColumnInfo(index = true)
    val country_id: String
)