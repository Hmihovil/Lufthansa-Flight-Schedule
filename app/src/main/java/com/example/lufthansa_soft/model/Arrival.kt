package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Arrival(

	@SerializedName("AirportCode")
	val airportCode: String? = null,

	@SerializedName("ScheduledTimeLocal")
	val scheduledTimeLocal: ScheduledTimeLocal? = null,

	@field:SerializedName("Terminal")
	val terminal: Terminal? = null
)