package com.example.lufthansa_soft.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.utils.showSnackbar
import com.example.lufthansa_soft.ui.adapter.AirportAdapter
import com.example.lufthansa_soft.viewModel.AirportState
import com.example.lufthansa_soft.viewModel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_display_airports.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ConstraintLayout>
    val viewModel: SharedViewModel by viewModel()
    var isTakeOff = false

    private val airportAdapter by lazy {
        AirportAdapter({ item, _ ->
            if (isTakeOff) {
                text_takeoff.setText(item.airportCode)
            } else {
                text_landing.setText(item.airportCode)
            }
            if (bottomSheetBehaviour.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        text_takeoff.setOnClickListener {
            isTakeOff = true
            toggleBottomSheet()
        }
        text_landing.setOnClickListener {
            isTakeOff = false
            toggleBottomSheet() }

        bottomSheetBehaviour = BottomSheetBehavior.from(bottom_sheet_layout)
        bottomSheetBehaviour.addBottomSheetCallback(object:
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    viewModel.getAirports()
                    setupRecycler()
                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                }
            }
        })

        viewModel.data.observe(this, Observer {
            when(it) {
                is AirportState.Success -> {
                    flight_progress.visibility = View.GONE
                    airportAdapter.updateList(it.airportList)
                }
                is AirportState.Error -> {
                    flight_progress.visibility = View.GONE
                    display_airport_wrapper.showSnackbar("Flight could not be loaded")
                }
            }
        })
    }

    private fun setupRecycler() {
        with(airport_list) {
            setHasFixedSize(true)
            layoutManager  = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = airportAdapter
        }
    }

    private fun toggleBottomSheet() {
        if (bottomSheetBehaviour.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

}
