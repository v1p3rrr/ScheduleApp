package com.vpr.scheduleapp.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.model.schedule.Direction
import com.vpr.scheduleapp.data.model.schedule.Schedule

class DirectionConverter {
    @TypeConverter
    fun fromList(value: List<Direction>): String {
        val type = object : TypeToken<List<Direction>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromString(value: String) : List<Direction>{
        val type = object : TypeToken<List<Direction>>() {}.type
        return Gson().fromJson(value, type)
    }
}