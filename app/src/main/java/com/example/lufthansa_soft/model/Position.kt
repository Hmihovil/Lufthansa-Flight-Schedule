package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Position(

	@field:SerializedName("Coordinate")
	val coordinate: Coordinate? = null
) : Serializable