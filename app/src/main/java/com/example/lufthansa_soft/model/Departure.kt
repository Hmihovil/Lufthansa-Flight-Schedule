package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Departure(

	@SerializedName("AirportCode")
	val airportCode: String? = null,

	@SerializedName("ScheduledTimeLocal")
	val scheduledTimeLocal: ScheduledTimeLocal? = null,

	@SerializedName("Terminal")
	val terminal: Terminal? = null
)