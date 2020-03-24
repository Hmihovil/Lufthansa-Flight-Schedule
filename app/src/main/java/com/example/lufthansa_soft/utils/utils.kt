package com.example.lufthansa_soft

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar


object Constants{
    val BASE_URL = "https://api.lufthansa.com/v1/"
    val CLIENT_ID = "f66texwn5b54b22vwjnq4afq"
    val CLIENT_SECRET = "3x6beQEMgN"
    val GRANT_TYPE = "client_credentials"
    val TOKEN = "TOKEN"
    val LUFTHANSA_SOFT = "LUFTHANSA_SOFT"


    fun isOnline(context: Context) : Boolean {
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectionManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}

fun ViewGroup.showSnackbar(message: String) =
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .show()

