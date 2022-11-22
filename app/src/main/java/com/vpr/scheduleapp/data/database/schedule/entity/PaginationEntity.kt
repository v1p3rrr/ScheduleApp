package com.vpr.scheduleapp.data.database.schedule.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpr.scheduleapp.domain.model.schedule.Pagination

@Entity(tableName = "pagination")
data class PaginationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo
    val limit: Int,
    @ColumnInfo
    val offset: Int,
    @ColumnInfo
    val total: Int
) {
    fun toPagination(): Pagination {
        return Pagination(
            limit = limit,
            offset = offset,
            total = total
        )
    }
}