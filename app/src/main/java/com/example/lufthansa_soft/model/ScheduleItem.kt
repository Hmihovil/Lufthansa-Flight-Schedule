package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class ScheduleItem(

	@SerializedName("Flight")
	val flight: Flight? = null,

	@SerializedName("TotalJourney")
	val totalJourney: TotalJourney? = null
)