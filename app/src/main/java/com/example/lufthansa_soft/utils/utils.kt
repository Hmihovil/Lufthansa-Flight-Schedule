package com.example.lufthansa_soft.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Constants{
    const val CLIENT_ID = "f66texwn5b54b22vwjnq4afq"
    const val CLIENT_SECRET = "3x6beQEMgN"
    const val GRANT_TYPE = "client_credentials"
    const val TOKEN = "TOKEN"
    const val LUFTHANSA_SOFT = "LUFTHANSA_SOFT"
    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm"
    const val HOUR_MINUTES = "hh:mm a"
    const val ARRIVAL = "ARRIVAL"
    const val DEPARTURE = "DEPARTURE"



    fun isOnline(context: Context) : Boolean {
         val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         val activeNetwork = connectionManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
//
//    fun convertDate(time: String) : String? {
//        val simpFormater = SimpleDateFormat(DATE_FORMAT)
//        val convertedDate: Date?
//        var formattedDate: String? = null
//        try {
//            convertedDate = simpFormater.parse(time)
//            formattedDate = SimpleDateFormat(HOUR_MINUTES)
//                .format(convertedDate)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//
//        return formattedDate
//    }
}

fun ViewGroup.showSnackbar(message: String, action: (View) -> Unit = {}) =
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).setAction("RETRY", action)
        .show()

