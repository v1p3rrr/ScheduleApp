package com.vpr.scheduleapp.data.database.stations.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.database.stations.entity.SettlementEntity

class SettlementConverter {
    @TypeConverter
    fun fromList(value: List<SettlementEntity>): String {
        val type = object : TypeToken<List<SettlementEntity>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromString(value: String) : List<SettlementEntity>{
        val type = object : TypeToken<List<SettlementEntity>>() {}.type
        return Gson().fromJson(value, type)
    }
}