package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Airports(

	@SerializedName("Airport")
	val airport: List<AirportItem>
)