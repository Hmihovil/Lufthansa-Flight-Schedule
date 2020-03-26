package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class DatePeriod(

	@field:SerializedName("Expiration")
	val expiration: String? = null,

	@field:SerializedName("Effective")
	val effective: String? = null
)