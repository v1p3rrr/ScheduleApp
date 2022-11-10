package com.vpr.scheduleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScheduleApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}