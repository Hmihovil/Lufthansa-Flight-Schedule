package com.example.lufthansa_soft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.databinding.AirlineScheduleItemBinding
import com.example.lufthansa_soft.model.testing.FlightItem
import com.example.lufthansa_soft.model.testing.Schedule

class AirlineSchedulesAdapter(val onClick: (item: FlightItem, view: View) -> Unit )
    : RecyclerView.Adapter<AirlineSchedulesAdapter.ViewHolder>() {

    private val flightItem = mutableListOf<FlightItem>()

    fun updateList(flightItemList: List<FlightItem>) {
        flightItem.clear()
        flightItem.addAll(flightItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<AirlineScheduleItemBinding>(
            inflater, R.layout.airline_schedule_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = flightItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = flightItem[position]
        holder.bind(createOnClick(items), items)
    }

    inner class ViewHolder(val binding: AirlineScheduleItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: FlightItem) {
//            item.flight?.forEach {
                binding.item = item
//            }
            binding.clicklistener = listener
            binding.executePendingBindings()
        }
    }

    private fun createOnClick(item: FlightItem) : View.OnClickListener {
        return View.OnClickListener {
            onClick(item, it)
        }
    }

}