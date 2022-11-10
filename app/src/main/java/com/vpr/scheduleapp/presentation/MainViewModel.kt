package com.vpr.scheduleapp.presentation

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpr.scheduleapp.data.api.ApiCallback
import com.vpr.scheduleapp.data.model.schedule.FetchedSchedule
import com.vpr.scheduleapp.data.model.stations.FetchedStations
import com.vpr.scheduleapp.data.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ScheduleRepository) : ViewModel() {

    private val _scheduleLiveData = MutableLiveData<FetchedSchedule>()
    val scheduleLiveData: LiveData<FetchedSchedule>
        get() = _scheduleLiveData

    private val _stationsLiveData = MutableLiveData<FetchedStations>()
    val stationsLiveData: LiveData<FetchedStations>
        get() = _stationsLiveData


    fun getSchedule(){
        repository.getScheduleDirectlyFromApi(object : ApiCallback<FetchedSchedule> {
            override fun onSuccess(t: FetchedSchedule?) {
                viewModelScope.launch {
                    t.let { fetchedSchedule ->
                        fetchedSchedule?.schedule?.forEach {
                            it.departure = convertTime(it.departure)
                            it.arrival = convertTime(it.arrival) //todo changed val to var - guess not the best practice
                        }
                        _scheduleLiveData.postValue(fetchedSchedule)
                    }
                }
            }

            override fun onError(error: String) {
                Log.e("msg: ", error)
            }

            override fun onException(exception: Throwable) {
                Log.e("msg: ", exception.message!!)
            }

        })
    }

    fun getStationsFromApi(){
        repository.getStationsFromApi(object : ApiCallback<FetchedStations> {
            override fun onSuccess(t: FetchedStations?) {
                t.let { _stationsLiveData.postValue(it) }
                Log.e("msg: ", "ok!")
            }

            override fun onError(error: String) {
                Log.e("msg: ", error)
            }

            override fun onException(exception: Throwable) {
                Log.e("msg: ", exception.message!!)
                exception.printStackTrace()
            }
        })
    }

    //todo in which layer is it better to do conversion? Also before or after storing it into db?
    private fun convertTime(time: String): String {
        val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale("ru", "RU"))
        val newFormat = SimpleDateFormat("HH:mm", Locale("ru", "RU"))
        val parsedTime: Date = initialFormat.parse(time)
        return newFormat.format(parsedTime)

    }
}