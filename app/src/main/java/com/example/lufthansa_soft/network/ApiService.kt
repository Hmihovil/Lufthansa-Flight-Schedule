package com.example.lufthansa_soft.network

import com.example.lufthansa_soft.model.TokenResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {


    @POST("oauth/token")
    fun retrieveToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String
    ) : Single<TokenResponse>
}