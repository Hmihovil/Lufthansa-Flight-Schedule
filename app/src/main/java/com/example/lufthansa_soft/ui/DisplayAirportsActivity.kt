package com.example.lufthansa_soft.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.showSnackbar
import com.example.lufthansa_soft.ui.adapter.AirportAdapter
import com.example.lufthansa_soft.viewModel.AirportState
import com.example.lufthansa_soft.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.activity_display_airports.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayAirportsActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by viewModel()

    private val airportAdapter by lazy {
        AirportAdapter({ view, item ->

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_airports)

        with(airport_list) {
            setHasFixedSize(true)
            layoutManager  = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = airportAdapter
        }

        viewModel.getAirports()

        viewModel.data.observe(this, Observer {
            when(it) {
                is AirportState.Success -> {
                    airportAdapter.updateList(it.airportList)
                }
                is AirportState.Error -> {
                    display_airport_wrapper.showSnackbar("")
                }
            }
        })
    }
}
