package com.example.lufthansa_soft.ui.airlineschedule

import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lufthansa_soft.R
import com.example.lufthansa_soft.model.AirportItem
import com.example.lufthansa_soft.utils.Constants
import com.example.lufthansa_soft.utils.Constants.ARRIVAL
import com.example.lufthansa_soft.utils.Constants.DEPARTURE

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class AirlineScheduleActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var departure: AirportItem? = null
    private var arrival: AirportItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airline_schedule)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        departure = intent.getSerializableExtra(ARRIVAL) as AirportItem
        arrival = intent.getSerializableExtra(DEPARTURE) as AirportItem

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val departureLatitude: Double? = departure!!.position?.coordinate?.latitude
        val departureLongitude = departure!!.position?.coordinate?.longitude

        val departureLatLng = LatLng(departureLatitude!!, departureLongitude!!)

        val departureMarker = googleMap.addMarker(MarkerOptions()
            .position(departureLatLng).title(departure!!.names?.name?.countryName))

        departureMarker.showInfoWindow()



        val arrivalLongitude = arrival!!.position?.coordinate?.longitude
        val arrivalLatitude = arrival!!.position?.coordinate?.latitude

        val arrivalLatLng = LatLng(arrivalLatitude!!, arrivalLongitude!!)

        val arrivalMarker = googleMap.addMarker(MarkerOptions()
            .position(arrivalLatLng).title(arrival!!.names?.name?.countryName))

        arrivalMarker.showInfoWindow()

        googleMap.addPolyline(PolylineOptions()
            .add(departureLatLng, arrivalLatLng).width(6f).color(Color.BLACK))

        googleMap.setOnMarkerClickListener (onMarkerClickedListener )

        val boundaries = LatLngBounds.Builder()
            .include(departureLatLng).include(arrivalLatLng).build()
        val displaySize = Point()
        windowManager.defaultDisplay.getSize(displaySize)
        googleMap.moveCamera(CameraUpdateFactory
            .newLatLngBounds(boundaries, displaySize.x, 150, 30))

        val cameraPosition = CameraPosition.Builder().target(
            arrivalLatLng
        ).zoom(1f).build()


        googleMap.animateCamera(
            CameraUpdateFactory.newCameraPosition(cameraPosition)
        )
    }

    private val onMarkerClickedListener = GoogleMap.OnMarkerClickListener { marker ->
        if (marker.isInfoWindowShown) {
            marker.showInfoWindow()
        } else {
            marker.showInfoWindow()
        }
        true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
