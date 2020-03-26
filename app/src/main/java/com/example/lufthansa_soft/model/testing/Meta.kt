package com.example.lufthansa_soft.model.testing

import com.google.gson.annotations.SerializedName

data class Meta(

	@field:SerializedName("@Version")
	val version: String? = null,

	@field:SerializedName("Link")
	val link: List<LinkItem?>? = null
)