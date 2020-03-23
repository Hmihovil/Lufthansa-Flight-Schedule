package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Details(

	@SerializedName("Stops")
	val stops: Stops? = null,

	@SerializedName("DaysOfOperation")
	val daysOfOperation: String? = null,

	@SerializedName("DatePeriod")
	val datePeriod: DatePeriod? = null
)