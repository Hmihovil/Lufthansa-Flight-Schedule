package com.example.lufthansa_soft.di


import com.example.lufthansa_soft.utils.Constants.BASE_URL
import com.example.lufthansa_soft.MyApplication
import com.example.lufthansa_soft.network.ApiService
import com.example.lufthansa_soft.viewModel.SharedViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    single  { provideInterceptor(MyApplication.pref.token) }
    single { provideHttpLoggingInterceptor() }
    factory(named("auth")) {
        provideHttpClientWithAuth(get(), get())
    }
    single {
        provideHttpClientWithoutAuth(get())
    }
    single (named("noAuth")){ provideApiServiceWithoutAuth(get()) }
    factory (named("auth")){ provideApiServiceWithAuth(get()) }

    viewModel { SharedViewModel(
        get(named("noAuth")), get(named("auth"))) }
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

fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
    return httpLoggingInterceptor
}

fun provideHttpClientWithAuth(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apikeyInterceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apikeyInterceptor)
        .build()
}

fun provideHttpClientWithoutAuth(
    httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}


fun provideApiServiceWithAuth(okHttpClient: OkHttpClient) : ApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}

fun provideApiServiceWithoutAuth(okHttpClient: OkHttpClient) : ApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}