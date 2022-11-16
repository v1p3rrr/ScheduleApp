package com.vpr.scheduleapp.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.database.entity.schedule.DirectionEntity

class DirectionConverter {
    @TypeConverter
    fun fromList(value: List<DirectionEntity>): String {
        val type = object : TypeToken<List<DirectionEntity>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromString(value: String) : List<DirectionEntity>{
        val type = object : TypeToken<List<DirectionEntity>>() {}.type
        return Gson().fromJson(value, type)
    }
}