package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class ScheduledTimeLocal(

	@field:SerializedName("DateTime")
	val dateTime: String? = null
)