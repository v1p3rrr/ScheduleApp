package com.vpr.scheduleapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpr.scheduleapp.data.remote.ApiCallback
import com.vpr.scheduleapp.data.remote.dto.stations.CountryDTO
import com.vpr.scheduleapp.data.repository.ScheduleRepositoryImpl
import com.vpr.scheduleapp.domain.model.schedule.FetchedSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ScheduleRepositoryImpl) :
    ViewModel() {

    private val _scheduleLiveData = MutableLiveData<FetchedSchedule?>()
    val scheduleLiveData: LiveData<FetchedSchedule?>
        get() = _scheduleLiveData

    private val _stationsLiveData = MutableLiveData<FetchedSchedule>()
    val stationsLiveData: LiveData<FetchedSchedule>
        get() = _stationsLiveData


    fun getSchedule() {
        repository.getScheduleDirectlyFromApi(object : ApiCallback<FetchedSchedule> {
            override fun onSuccess(t: FetchedSchedule?) {
                    t.let { fetchedSchedule ->
                        _scheduleLiveData.postValue(fetchedSchedule)
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

    fun getScheduleByStationAndDate(stationCode: String, date: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _scheduleLiveData.postValue(
                    repository.getScheduleByStationCodeAndDate(
                        stationCode,
                        date
                    )
                )
            }
        }
    }

    //todo remove this bullshit from viewmodel
    fun getStationsFromApi() {
        repository.getStationsFromApi(object : ApiCallback<List<CountryDTO>> {
            override fun onSuccess(t: List<CountryDTO>?) {
                t.let {
                    //kys _stationsLiveData.postValue(it)
                }
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

    //todo in which layer is it better to do conversion?
    // Also before or after storing it into db?
    // Where to calculate travel time difference?
    // (do it in domain module in dto fun)

}