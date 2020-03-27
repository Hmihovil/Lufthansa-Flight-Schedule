package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class FlightItem(

	@field:SerializedName("Details")
	val details: Details? = null,

	@field:SerializedName("Equipment")
	val equipment: Equipment? = null,

	@field:SerializedName("Departure")
	val departure: Departure? = null,

	@field:SerializedName("MarketingCarrier")
	val marketingCarrier: MarketingCarrier? = null,

	@field:SerializedName("Arrival")
	val arrival: Arrival? = null,

	@field:SerializedName("OperatingCarrier")
	val operatingCarrier: OperatingCarrier? = null
)