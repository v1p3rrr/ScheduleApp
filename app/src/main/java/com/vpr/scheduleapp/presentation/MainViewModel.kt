package com.vpr.scheduleapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.vpr.scheduleapp.domain.model.schedule.Schedule
import com.vpr.scheduleapp.domain.model.schedule.Segment
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

    private val _scheduleListState = mutableStateOf(SegmentScheduleState())
    val scheduleListState: State<SegmentScheduleState> = _scheduleListState

    private val _errorMessageSharedFlow = MutableSharedFlow<String>()
    val errorMessageSharedFlow = _errorMessageSharedFlow.asSharedFlow()

    private var _scheduleLiveData = MutableLiveData<List<Segment?>>(emptyList())
    val scheduleLiveData: LiveData<List<Segment?>>
        get() = _scheduleLiveData

    private val _stationsLiveData = MutableLiveData<StationSchedule>()
    val stationsLiveData: LiveData<StationSchedule>
        get() = _stationsLiveData

    fun getScheduleByStationCodeAndDate(from: String, to: String, date: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getScheduleByStationCodeAndDate(from, to, date).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _scheduleListState.value = scheduleListState.value.copy(
                                segment = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message?: "Success")

                        }
                        is Resource.Error -> {
                            _scheduleListState.value = scheduleListState.value.copy(
                                segment = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _errorMessageSharedFlow.emit(result.message?: "Unknown error")
                        }
                        is Resource.Loading -> {
                            _scheduleListState.value = scheduleListState.value.copy(
                                segment = result.data ?: emptyList(),
                                isLoading = true
                            )
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