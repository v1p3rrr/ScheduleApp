package com.vpr.scheduleapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.vpr.scheduleapp.domain.model.schedule.Schedule
import com.vpr.scheduleapp.domain.model.schedule.StationSchedule
import com.vpr.scheduleapp.domain.repository.ScheduleRepository
import com.vpr.scheduleapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ScheduleRepository) :
    ViewModel() {

    private val _scheduleListState = mutableStateOf(emptyList<Schedule>())
    val scheduleListState: State<List<Schedule>> = _scheduleListState

    private val _errorMessageSharedFlow = MutableSharedFlow<String>()
    val errorMessageSharedFlow = _errorMessageSharedFlow.asSharedFlow()

    private var _scheduleLiveData = MutableLiveData<List<Schedule>>(emptyList())
    val scheduleLiveData: LiveData<List<Schedule>>
        get() = _scheduleLiveData

    private val _stationsLiveData = MutableLiveData<StationSchedule>()
    val stationsLiveData: LiveData<StationSchedule>
        get() = _stationsLiveData


    fun getScheduleByStationCodeAndDate(stationCode: String, date: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getScheduleByStationCodeAndDate(
                    stationCode,
                    date
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _scheduleListState.value = result.data?.schedule ?: emptyList()
                        }
                        is Resource.Error -> {
                            _errorMessageSharedFlow.emit(result.message?: "Unknown error")
                        }
                        is Resource.Loading -> {
                            _errorMessageSharedFlow.emit(result.message?: "Loading")
                        }
                    }
                }.launchIn(this)
            }
        }
    }


    //todo in which layer is it better to do conversion?
    // Also before or after storing it into db?
    // Where to calculate travel time difference?
    // (do it in domain module in dto fun)

}