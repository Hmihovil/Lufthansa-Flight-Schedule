package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class CompartmentItem(

	@SerializedName("SeatPower")
	val seatPower: Boolean? = null,

	@SerializedName("LiveTv")
	val liveTv: Boolean? = null,

	@SerializedName("ClassCode")
	val classCode: String? = null,

	@SerializedName("Usb")
	val usb: Boolean? = null,

	@SerializedName("FlyNet")
	val flyNet: Boolean? = null,

	@SerializedName("ClassDesc")
	val classDesc: String? = null
)