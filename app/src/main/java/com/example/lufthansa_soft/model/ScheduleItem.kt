package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class ScheduleItem(

	@field:SerializedName("Flight")
	val flight: Flight? = null,

	@field:SerializedName("TotalJourney")
	val totalJourney: TotalJourney? = null
)