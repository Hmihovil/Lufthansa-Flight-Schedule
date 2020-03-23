package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class OnBoardEquipment(

	@SerializedName("InflightEntertainment")
	val inflightEntertainment: Boolean? = null,

	@SerializedName("Compartment")
	val compartment: List<CompartmentItem?>? = null
)