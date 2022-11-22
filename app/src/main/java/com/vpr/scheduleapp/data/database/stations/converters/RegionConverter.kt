package com.vpr.scheduleapp.data.database.stations.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.database.stations.entity.*

class RegionConverter {
    @TypeConverter
    fun fromList(value: List<RegionEntity>): String {
        val type = object : TypeToken<List<RegionEntity>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromString(value: String) : List<RegionEntity>{
        val type = object : TypeToken<List<RegionEntity>>() {}.type
        return Gson().fromJson(value, type)
    }
}