package com.vpr.scheduleapp.data.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
//import com.vpr.scheduleapp.data.model.schedule.Direction
//import com.vpr.scheduleapp.data.model.schedule.Schedule
//import com.vpr.scheduleapp.data.model.schedule.ScheduleDirection
//import com.vpr.scheduleapp.data.model.schedule.Station
//import com.vpr.scheduleapp.data.model.schedule.Thread
//import com.vpr.scheduleapp.data.model.schedule.TransportSubtype
//import com.vpr.scheduleapp.data.model.schedule.Codes
//import com.vpr.scheduleapp.data.model.schedule.Carrier
//import com.vpr.scheduleapp.data.model.schedule.Pagination
//
//
//@Database(entities = [FetchedSchedule::class, Direction::class, Schedule::class, ScheduleDirection::class, Station::class, Thread::class, TransportSubtype::class, Codes::class, Carrier::class, Pagination::class], version = 0)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun scheduleDao() : ScheduleDao
//
//    companion object {
//
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabaseInstance(context: Context) : AppDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}