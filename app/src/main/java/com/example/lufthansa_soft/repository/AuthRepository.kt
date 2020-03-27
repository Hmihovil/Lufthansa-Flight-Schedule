package com.example.lufthansa_soft.repository

import com.example.lufthansa_soft.MyApplication

class AuthRepository {

    val hasAuthToken: Boolean?
        get() = MyApplication.pref.token.isNotEmpty()

}