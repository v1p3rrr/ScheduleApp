package com.vpr.scheduleapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vpr.scheduleapp.data.api.ApiCallback
import com.vpr.scheduleapp.data.database.AppDatabase
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.schedule.Schedule
import com.vpr.scheduleapp.data.repository.ScheduleRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ScheduleRepository(AppDatabase.getDatabaseInstance(application).scheduleDao())

    private val _scheduleLiveData = MutableLiveData<FetchedSchedule>()
    val scheduleLiveData: LiveData<FetchedSchedule>
        get() = _scheduleLiveData


    fun getSchedule(){
        repository.getSchedule(object : ApiCallback<FetchedSchedule> {
            override fun onSuccess(t: FetchedSchedule?) {
                t.let { _scheduleLiveData.postValue(it) }
            }

            override fun onError(error: String) {
                Log.e("msg: ", error)
            }

            override fun onException(exception: Throwable) {
                Log.e("msg: ", exception.message!!)
            }

        })
    }
}