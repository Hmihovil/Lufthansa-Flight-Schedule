package com.example.lufthansa_soft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.databinding.AirportItemBinding
import com.example.lufthansa_soft.model.AirportItem
import kotlinx.android.synthetic.main.activity_display_airports.view.*

class AirportAdapter(val onClick : (item: AirportItem, view: View) -> Unit):
    RecyclerView.Adapter<AirportAdapter.ViewHolder>() {

    private val airports = mutableListOf<AirportItem>()

    fun updateList(userList: List<AirportItem>) {
        airports.clear()
        airports.addAll(userList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<AirportItemBinding>(
            inflater, R.layout.airport_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = airports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = airports[position]
        holder.bind(createOnClick(items), items)
    }

    inner class ViewHolder(val binding: AirportItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: AirportItem) {
            binding.item = item
            binding.clicklistener = listener
            binding.executePendingBindings()
        }
    }

    private fun createOnClick(item : AirportItem): View.OnClickListener {
        return View.OnClickListener {
            onClick(item, it)
        }
    }
}