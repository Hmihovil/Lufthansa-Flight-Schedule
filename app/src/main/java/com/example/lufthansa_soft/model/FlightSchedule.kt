package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class FlightSchedule(

	@SerializedName("ScheduleResource")
	val scheduleResource: ScheduleResource? = null
)