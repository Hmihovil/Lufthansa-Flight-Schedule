package com.example.lufthansa_soft.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lufthansa_soft.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//        supportActionBar?.title = null

        image_takeoff.setOnClickListener { _ -> goToAirportsActivity() }
        image_landing.setOnClickListener { _ -> goToAirportsActivity() }
    }

    private fun goToAirportsActivity() {
        Intent(this, DisplayAirportsActivity::class.java).apply {
            startActivity(this)
        }
    }

}
