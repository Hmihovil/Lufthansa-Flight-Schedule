package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Name(

	@SerializedName("@LanguageCode")
	val languageCode: String? = null,

	@SerializedName("$")
	val countryName: String? = null
)