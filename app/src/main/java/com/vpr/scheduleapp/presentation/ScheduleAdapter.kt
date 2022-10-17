package com.vpr.scheduleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vpr.scheduleapp.R
import com.vpr.scheduleapp.data.model.schedule.Schedule
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAdapter() : ListAdapter<Schedule, ScheduleAdapter.MyViewHolder>(DiffCallback()) {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trainTv: TextView = itemView.findViewById(R.id.tvTrain)
        val departureTv: TextView = itemView.findViewById(R.id.tvDepartureTime)
        val arrivalTv: TextView = itemView.findViewById(R.id.tvArrivalTime)
        val travelTv: TextView = itemView.findViewById(R.id.tvTravelTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.trainTv.text = getItem(position).thread.short_title
        holder.departureTv.text = getItem(position).departure
        holder.arrivalTv.text = getItem(position).arrival
        //holder.travelTv.text = getItem(position).thread.short_title
    }

//    private fun convertTime(time: String) : String {
//        val parseFormat = SimpleDateFormat("yyyy-MM-dd'T'00:hh:mm.ssZZZZZ", Locale("ru","RU"))
//        val parsedTime = parseFormat.parse(time)
//        return SimpleDateFormat("dd.MMMM HH:mm", Locale("ru","RU")).format(parsedTime)
//    }

    class DiffCallback : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.thread.uid == newItem.thread.uid
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }
    }

}