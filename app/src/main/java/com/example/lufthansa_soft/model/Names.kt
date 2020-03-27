package com.example.lufthansa_soft.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Names(

	@SerializedName("Name")
	val name: Name? = null
) : Serializable