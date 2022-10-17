package com.vpr.scheduleapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vpr.scheduleapp.databinding.ActivityScheduleBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var scheduleAdapter: ScheduleAdapter

    private lateinit var vm : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scheduleAdapter = ScheduleAdapter()

        binding.scheduleRecycler.apply {
            adapter = scheduleAdapter;
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        vm.scheduleLiveData.observe(this) {
            scheduleAdapter.submitList(it.schedule)
        }

//        vm.stationsLiveData.observe(this) {
//            binding.btnToday.text =  it.countries[it.countries.indexOfFirst {
//                it.title == "Россия"
//            }].title
//
//            Log.d("api", it.countries.joinToString())
//        }

        vm.getSchedule()
        //vm.getStationsFromApi()
    }
}