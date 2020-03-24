package com.example.lufthansa_soft.network

import com.example.lufthansa_soft.model.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("oauth/token")
    fun retrieveToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String
    ) : Single<TokenResponse>

    @Headers(
        "Accept: application/json",
        "Authorization: Bearer srt6peyyed3k2s238cmptn4z"
    )
    @GET("mds-references/airports?limit=200&offset=0&LHoperated=0&lang=en")
    fun getAirports(): Single<Aiports>

    @GET("/operations/schedules/{origin}/{destination}/{fromDateTime}")
    fun getAirlineSchedules(
        @Path(value = "origin") origin: String,
        @Path("destination") destination: String,
        @Path("fromDateTime") fromDateTime: String
    ): Single<FlightSchedule>



}