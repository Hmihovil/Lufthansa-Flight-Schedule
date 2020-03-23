package com.example.lufthansa_soft.di

import com.example.lufthansa_soft.Constants.BASE_URL
import com.example.lufthansa_soft.network.ApiService
import com.example.lufthansa_soft.viewModel.SharedViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideHttpInterceptor() }
    single { provideInterceptor("8wauyzm9nzkjkvv7dff7xsv7") }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    viewModel { SharedViewModel(get()) }
}

fun provideInterceptor(authToken: String) : Interceptor {
    return Interceptor {
        val original = it.request()

        val url = original.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        it.proceed(url)
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()


fun provideHttpInterceptor() : HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return httpLoggingInterceptor
}

fun provideOkHttpClient(apikeyInterceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(apikeyInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)