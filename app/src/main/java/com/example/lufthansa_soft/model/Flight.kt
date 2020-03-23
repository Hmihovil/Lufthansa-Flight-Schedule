package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Flight(

	@SerializedName("Details")
	val details: Details? = null,

	@SerializedName("Equipment")
	val equipment: Equipment? = null,

	@SerializedName("Departure")
	val departure: Departure? = null,

	@SerializedName("MarketingCarrier")
	val marketingCarrier: MarketingCarrier? = null,

	@SerializedName("Arrival")
	val arrival: Arrival? = null
)