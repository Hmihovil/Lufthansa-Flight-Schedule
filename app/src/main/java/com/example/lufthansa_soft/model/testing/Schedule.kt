package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class Schedule(

	@field:SerializedName("Flight")
	val flight: List<FlightItem>? = null,

	@field:SerializedName("TotalJourney")
	val totalJourney: TotalJourney? = null
)