package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class Details(

	@field:SerializedName("Stops")
	val stops: Stops? = null,

	@field:SerializedName("DaysOfOperation")
	val daysOfOperation: String? = null,

	@field:SerializedName("DatePeriod")
	val datePeriod: DatePeriod? = null
)