package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class ScheduleResource(

	@field:SerializedName("Meta")
	val meta: Meta? = null,

	@field:SerializedName("Schedule")
	val schedule: Schedule? = null
)