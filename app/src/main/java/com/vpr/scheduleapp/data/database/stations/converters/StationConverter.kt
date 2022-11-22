package com.vpr.scheduleapp.data.database.stations.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.database.stations.entity.StationEntity


class StationConverter {
    @TypeConverter
    fun fromList(value: List<StationEntity>): String {
        val type = object : TypeToken<List<StationEntity>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromString(value: String) : List<StationEntity>{
        val type = object : TypeToken<List<StationEntity>>() {}.type
        return Gson().fromJson(value, type)
    }
}