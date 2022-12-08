package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.TransportSubtype

@Entity(tableName = "transport_subtype")
data class TransportSubtypeEntity(
    @PrimaryKey
    val code: String,
    @ColumnInfo
    val color: String,
    @ColumnInfo
    val subtype_title: String
) {
    fun toTransportSubtype(): TransportSubtype {
        return TransportSubtype(
            code = code,
            color = color,
            title = subtype_title
        )
    }
}