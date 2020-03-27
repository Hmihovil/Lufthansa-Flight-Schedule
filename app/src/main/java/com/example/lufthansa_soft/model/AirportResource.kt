package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class AirportResource(

	@SerializedName("Meta")
	val meta: Meta? = null,

	@SerializedName("Airports")
	val airports: Airports? = null
)