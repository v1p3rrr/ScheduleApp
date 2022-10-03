package com.vpr.scheduleapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vpr.scheduleapp.data.api.ApiCallback
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.schedule.Schedule
import com.vpr.scheduleapp.data.repository.ScheduleRepository

class MainViewModel() : ViewModel() {

    val repository = ScheduleRepository()

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