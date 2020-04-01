package com.example.lufthansa_soft.repository


import com.example.lufthansa_soft.model.Aiports
import com.example.lufthansa_soft.model.TokenResponse
import com.example.lufthansa_soft.model.testing.RootModel
import com.example.lufthansa_soft.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class Repository(private val apiService: ApiService) {

    fun getToken(client_id: String, client_secret: String,
                 grant_type: String) : Single<TokenResponse> {
        return apiService.retrieveToken(client_id, client_secret,
            grant_type)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getAirports(): Single<Aiports> {
        return apiService
            .getAirports()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getSchedules(origin: String,
                     destination: String, time: String) : Single<RootModel> {
        return apiService
            .getAirlineSchedules(origin, destination, time)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}