package com.vpr.scheduleapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vpr.scheduleapp.databinding.ActivityScheduleBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}