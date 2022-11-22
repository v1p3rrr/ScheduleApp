package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Threadd

@Entity(tableName = "thread")
data class ThreadEntity(
    @PrimaryKey
    val uid: String,
    @ColumnInfo
    val express_type: String?,
    @ColumnInfo
    val number: String?,
    @ColumnInfo
    val short_title: String?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val transport_subtype: TransportSubtypeEntity?,
    @ColumnInfo
    val transport_type: String?,
    @ColumnInfo
    val vehicle: String?
) {
    fun toThreadd(): Threadd {
        return Threadd(
            uid = uid,
            express_type = express_type,
            number = number,
            short_title = short_title,
            title = title,
            transport_subtype = transport_subtype?.toTransportSubtype(),
            transport_type = transport_type,
            vehicle = vehicle,
        )
    }
}