package com.example.lufthansa_soft.repository

import com.example.lufthansa_soft.utils.SharedPrefs

class AuthRepository(private val token : SharedPrefs) {

    val hasAuthToken: Boolean?
        get() = token.token.isNotEmpty()

}