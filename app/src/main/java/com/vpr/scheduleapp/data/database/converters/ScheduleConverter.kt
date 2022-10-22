package com.vpr.scheduleapp.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpr.scheduleapp.data.model.schedule.Schedule

class ScheduleConverter {
        @TypeConverter
        fun fromList(value: List<Schedule>): String
        {
            val type = object : TypeToken<List<Schedule>>() {}.type
            return Gson().toJson(value, type)
        }

        @TypeConverter
        fun fromString(value: String): List<Schedule> {
            //todo is null possible?
            val type = object : TypeToken<List<Schedule>>() {}.type
            return Gson().fromJson(value, type)
        }

}