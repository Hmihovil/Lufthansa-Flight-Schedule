package com.example.lufthansa_soft

import com.example.lufthansa_soft.di.appModule
import com.example.lufthansa_soft.network.ApiService
import junit.framework.Assert.assertNotNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

class NetworkTest: KoinTest {

    val baseUrl: String by lazy {  get(named("BASE_URL")) as String }
    val okHttpClient: OkHttpClient by lazy  {  get(named("auth")) as OkHttpClient }
    val httpLogger: HttpLoggingInterceptor by inject()

    @Before
    fun setup() {
        startKoin {
            modules(listOf(appModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Test base url`() {
        assert(baseUrl == "https://api.lufthansa.com/v1/")
    }

    @Test
    fun `Test http logger is not null` (){
        assertNotNull(httpLogger)
    }

    @Test
    fun `Test OkHttp client` () {
        assertNotNull(okHttpClient)
    }

}