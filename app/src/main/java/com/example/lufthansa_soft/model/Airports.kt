package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Airports(

	@SerializedName("Airport")
	val airport: List<AirportItem>
) : Serializable