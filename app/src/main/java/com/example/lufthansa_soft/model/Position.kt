package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Position(

	@field:SerializedName("Coordinate")
	val coordinate: Coordinate? = null
)