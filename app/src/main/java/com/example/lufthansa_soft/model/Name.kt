package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Name(

	@SerializedName("@LanguageCode")
	val languageCode: String? = null,

	@SerializedName("$")
	val countryName: String? = null
) : Serializable