package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class Meta(

	@SerializedName("@Version")
	val version: String? = null,

	@SerializedName("TotalCount")
	val totalCount: Int? = null,

	@SerializedName("Link")
	val link: List<LinkItem?>? = null

)