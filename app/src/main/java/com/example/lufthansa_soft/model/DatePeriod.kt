package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class DatePeriod(

	@SerializedName("Expiration")
	val expiration: String? = null,

	@SerializedName("Effective")
	val effective: String? = null
)