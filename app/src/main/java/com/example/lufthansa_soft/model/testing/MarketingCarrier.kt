package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class MarketingCarrier(

	@field:SerializedName("AirlineID")
	val airlineID: String? = null,

	@field:SerializedName("FlightNumber")
	val flightNumber: String? = null
)