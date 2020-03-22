package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(

	@SerializedName("access_token")
	val accessToken: String? = null,

	@SerializedName("token_type")
	val tokenType: String? = null,

	@SerializedName("expires_in")
	val expiresIn: Int? = null
)