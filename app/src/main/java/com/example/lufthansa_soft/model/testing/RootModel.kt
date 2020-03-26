package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class RootModel(

	@field:SerializedName("ScheduleResource")
	val scheduleResource: ScheduleResource? = null
)