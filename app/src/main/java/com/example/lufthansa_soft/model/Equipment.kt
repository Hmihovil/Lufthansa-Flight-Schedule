package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Equipment(

	@SerializedName("AircraftCode")
	val aircraftCode: String? = null,

	@SerializedName("OnBoardEquipment")
	val onBoardEquipment: OnBoardEquipment? = null
)