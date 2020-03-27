package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class MarketingCarrier(

	@SerializedName("AirlineID")
	val airlineID: String? = null,

	@SerializedName("FlightNumber")
	val flightNumber: String? = null
)