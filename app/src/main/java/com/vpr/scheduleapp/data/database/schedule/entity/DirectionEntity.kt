package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Directions

@Entity(tableName = "direction")
data class DirectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo
    val code: String,
    @ColumnInfo
    val title: String
) {
    fun toDirections(): Directions {
        return Directions(
            code = code,
            title = title
        )
    }
}