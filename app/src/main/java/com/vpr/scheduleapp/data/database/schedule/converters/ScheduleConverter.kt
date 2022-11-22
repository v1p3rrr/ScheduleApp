package com.vpr.scheduleapp.data.database.schedule.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.database.schedule.entity.ScheduleEntity

class ScheduleConverter {
        @TypeConverter
        fun fromList(value: List<ScheduleEntity>): String
        {
            val type = object : TypeToken<List<ScheduleEntity>>() {}.type
            return Gson().toJson(value, type)
        }

        @TypeConverter
        fun fromString(value: String): List<ScheduleEntity> {
            //todo is null possible?
            val type = object : TypeToken<List<ScheduleEntity>>() {}.type
            return Gson().fromJson(value, type)
        }

}
