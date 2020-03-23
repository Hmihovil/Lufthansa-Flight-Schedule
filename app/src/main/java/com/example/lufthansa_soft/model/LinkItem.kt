package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class LinkItem(

	@SerializedName("@Href")
	val href: String? = null,

	@SerializedName("@Rel")
	val rel: String? = null
)