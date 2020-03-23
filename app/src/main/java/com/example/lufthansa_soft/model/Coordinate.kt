package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Coordinate(

	@SerializedName("Latitude")
	val latitude: Double? = null,

	@SerializedName("Longitude")
	val longitude: Double? = null
)