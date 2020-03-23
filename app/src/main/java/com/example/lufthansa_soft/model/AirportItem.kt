package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class AirportItem(

	@SerializedName("CityCode")
	val cityCode: String? = null,

	@SerializedName("Names")
	val names: Names? = null,

	@SerializedName("UtcOffset")
	val utcOffset: String? = null,

	@SerializedName("AirportCode")
	val airportCode: String? = null,

	@SerializedName("Position")
	val position: Position? = null,

	@SerializedName("TimeZoneId")
	val timeZoneId: String? = null,

	@SerializedName("CountryCode")
	val countryCode: String? = null,

	@SerializedName("LocationType")
	val locationType: String? = null
)