package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coordinate(

	@SerializedName("Latitude")
	val latitude: Double? = null,

	@SerializedName("Longitude")
	val longitude: Double? = null
) : Serializable