package com.example.lufthansa_soft.model.testing

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

data class ScheduleResource(

	@SerializedName("Meta")
	val meta: Meta? = null,

	@SerializedName("Schedule")
	val schedule: List<Schedule>? = null
)