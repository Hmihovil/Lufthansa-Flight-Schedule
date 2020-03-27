package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class LinkItem(

	@field:SerializedName("@Href")
	val href: String? = null,

	@field:SerializedName("@Rel")
	val rel: String? = null
)