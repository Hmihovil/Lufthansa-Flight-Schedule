package com.example.lufthansa_soft.ui.main_ui

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.model.AirportItem
import com.example.lufthansa_soft.ui.adapter.AirlineSchedulesAdapter
import com.example.lufthansa_soft.utils.showSnackbar
import com.example.lufthansa_soft.ui.adapter.AirportAdapter
import com.example.lufthansa_soft.ui.airlineschedule.AirlineScheduleActivity
import com.example.lufthansa_soft.utils.Constants.ARRIVAL
import com.example.lufthansa_soft.utils.Constants.DEPARTURE
import com.example.lufthansa_soft.viewModel.AirportState
import com.example.lufthansa_soft.viewModel.FlightScheduleState
import com.example.lufthansa_soft.viewModel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ConstraintLayout>
    val viewModel: SharedViewModel by viewModel()
    var isTakeOff = false
    var dateOfSchedule: String? = null

    private var arrival: AirportItem? = null
    private var departure: AirportItem? = null

    private val airportAdapter by lazy {
        AirportAdapter({ item, _ ->
            if (isTakeOff) {
                departure = item
                text_takeoff.setText(item.airportCode)
            } else {
                arrival = item
                text_landing.setText(item.airportCode)
            }
            if (bottomSheetBehaviour.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        })
    }

    private val scheduleAdapter by lazy {
        AirlineSchedulesAdapter({ item, _ ->
            goToMapsActivity()
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

        select_date.setOnClickListener { openDatePicker() }
        check_schedule.setOnClickListener{
            collectSchedule()}

        with(schedule_list) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            adapter = scheduleAdapter
        }

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
                    main_layout.showSnackbar("Flight could not be loaded")
                    if (bottomSheetBehaviour.state != BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    } else {
                        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }
            }
        })

        viewModel.flightScheduleData.observe(this, Observer {
            when(it) {
                is FlightScheduleState.Success -> {
                    progress_bar_schedule.visibility = View.GONE
                    scheduleAdapter.updateList(it.schedules)
                }
                is FlightScheduleState.Error -> {
                    progress_bar_schedule.visibility = View.GONE
                    main_layout.showSnackbar("Schedules could not be found for the location")
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

    private fun goToMapsActivity() {
        val intent = Intent(this, AirlineScheduleActivity::class.java)
        intent.putExtra(ARRIVAL, arrival)
        intent.putExtra(DEPARTURE, departure)
        startActivity(intent)
    }

    private fun toggleBottomSheet() {
        if (bottomSheetBehaviour.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun openDatePicker() {
        val currentCalendar = Calendar.getInstance()
        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val date_picker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val format = SimpleDateFormat("yyyy-MM-dd")
            val date = calendar.time
            val formattedDate = format.format(date)
            date_schedule.setText(formattedDate)
            dateOfSchedule = formattedDate
        }

        val dateDialog = DatePickerDialog(this,
            date_picker, currentYear, currentMonth, currentDay)
        dateDialog.show()
    }

    private fun collectSchedule() {
        val arrivalText = text_takeoff.text.toString()
        val departureText = text_landing.text.toString()
        val dateSchedule = dateOfSchedule
        if ((arrivalText.isNotEmpty()
                    && departureText.isNotEmpty() && dateSchedule != null) ) {
            if (departureText != arrivalText ) {
                progress_bar_schedule.visibility = View.VISIBLE
                viewModel.getSchedules(arrivalText, departureText, dateSchedule)
            } else {
                main_layout.showSnackbar("Origin Code has to be different from Arrival Code")
            }
        } else {
            main_layout.showSnackbar("Fields cannot be empty")
        }
    }

}
