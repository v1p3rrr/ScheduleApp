package com.vpr.scheduleapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.vpr.scheduleapp.databinding.ActivityScheduleBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding

    val vm: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.scheduleLiveData.observe(this) {
            binding.btnToday.text = it.station.station_type
        }
        vm.getSchedule()
    }
}