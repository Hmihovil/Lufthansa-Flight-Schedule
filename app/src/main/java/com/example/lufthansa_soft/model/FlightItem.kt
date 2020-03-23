package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class FlightItem(

	@SerializedName("Details")
	val details: Details? = null,

	@SerializedName("Equipment")
	val equipment: Equipment? = null,

	@SerializedName("Departure")
	val departure: Departure? = null,

	@SerializedName("MarketingCarrier")
	val marketingCarrier: MarketingCarrier? = null,

	@SerializedName("Arrival")
	val arrival: Arrival? = null,

	@SerializedName("OperatingCarrier")
	val operatingCarrier: OperatingCarrier? = null
)