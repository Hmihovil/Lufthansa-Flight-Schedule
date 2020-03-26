package com.example.lufthansa_soft.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val authToken: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
            .addHeader("X-Originating-IP", "41.210.154.244")
            .header("Authorization", "Bearer $authToken")

        val request = builder.build()
        return chain.proceed(request)
    }
}