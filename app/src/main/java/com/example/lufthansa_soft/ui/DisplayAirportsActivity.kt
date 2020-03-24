package com.example.lufthansa_soft.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.ui.adapter.AirportAdapter
import com.example.lufthansa_soft.viewModel.SharedViewModel
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
//        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Select Flight"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.getAirports()

//        with(airport_list) {
//            setHasFixedSize(true)
//            layoutManager  = LinearLayoutManager(
//                context,
//                LinearLayoutManager.VERTICAL, false
//            )
//            adapter = airportAdapter
//        }


//        viewModel.data.observe(this, Observer {
//            when(it) {
//                is AirportState.Success -> {
//                    flight_progress.visibility = View.GONE
//                    airportAdapter.updateList(it.airportList)
//                }
//                is AirportState.Error -> {
//                    flight_progress.visibility = View.GONE
//                    display_airport_wrapper.showSnackbar("Flight could not be loaded")
//                }
//            }
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
