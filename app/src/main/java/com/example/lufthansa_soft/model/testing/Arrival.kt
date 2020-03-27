package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class Arrival(

	@field:SerializedName("AirportCode")
	val airportCode: String? = null,

	@field:SerializedName("ScheduledTimeLocal")
	val scheduledTimeLocal: ScheduledTimeLocal? = null,

	@field:SerializedName("Terminal")
	val terminal: Terminal? = null
)