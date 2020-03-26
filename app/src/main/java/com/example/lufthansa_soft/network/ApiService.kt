package com.example.lufthansa_soft.network

import com.example.lufthansa_soft.model.Aiports
import com.example.lufthansa_soft.model.TokenResponse
import com.example.lufthansa_soft.model.testing.RootModel
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
        "Authorization: Bearer vq9tntzj9s9ykf42nb362vrv"
    )
    @GET("mds-references/airports?limit=200&offset=0&LHoperated=1&lang=en&recordLimit=100")
    fun getAirports(): Single<Aiports>

    @Headers(
        "Accept: application/json",
        "Authorization: Bearer vq9tntzj9s9ykf42nb362vrv"
    )
    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    fun getAirlineSchedules(
        @Path("origin") origin: String,
        @Path("destination") destination: String,
        @Path("fromDateTime") fromDateTime: String
    ): Single<RootModel>



}