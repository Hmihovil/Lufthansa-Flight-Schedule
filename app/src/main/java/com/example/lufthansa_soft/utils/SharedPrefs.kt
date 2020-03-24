package com.example.lufthansa_soft.utils

import android.content.Context
import com.example.lufthansa_soft.utils.Constants.LUFTHANSA_SOFT
import com.example.lufthansa_soft.utils.Constants.TOKEN

class SharedPrefs(context: Context) {

    val sharedPreferences = context.getSharedPreferences(LUFTHANSA_SOFT, Context.MODE_PRIVATE)

    var token: String
        get() = sharedPreferences.getString(TOKEN, "")!!
        set(value) = sharedPreferences.edit().putString(TOKEN, value).apply()

}