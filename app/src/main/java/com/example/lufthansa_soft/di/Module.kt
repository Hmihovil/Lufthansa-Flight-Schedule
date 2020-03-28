package com.example.lufthansa_soft.di


import android.content.Context
import com.example.lufthansa_soft.repository.AuthRepository
import com.example.lufthansa_soft.MyApplication
import com.example.lufthansa_soft.repository.Repository
import com.example.lufthansa_soft.network.ApiService
import com.example.lufthansa_soft.utils.SharedPrefs
import com.example.lufthansa_soft.viewModel.SharedViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {

    single(named("BASE_URL")) {
        "https://api.lufthansa.com/v1/"
    }
    single {  provideSharedPreferences(androidContext())}
    single  {
        val token = get<SharedPrefs>().token
        provideInterceptor(token)
    }
    single { provideHttpLoggingInterceptor() }
    factory { provideAuthRepository(get()) }
    factory (named("auth")) {
        provideHttpClientWithAuth(get(), get())
    }
    factory (named("noAuth")){
        provideHttpClientWithoutAuth(get())
    }
    factory {
        val hasToken = get<AuthRepository>().hasAuthToken
        if (hasToken!!) {
            val authApi = get<OkHttpClient>(named("auth"))
            val baseUrl = get<String>(named("BASE_URL"))
            provideApiServiceWithAuth(authApi, baseUrl)
        } else {
            val noAuthApi = get<OkHttpClient>(named("noAuth"))
            val baseUrl = get<String>(named("BASE_URL"))
            provideApiServiceWithoutAuth(noAuthApi, baseUrl)
        }
    }
    factory { provideRepository(get()) }
    viewModel { SharedViewModel(get()) }
}

fun provideSharedPreferences(context: Context) = SharedPrefs(context)

fun provideInterceptor(authToken: String) : Interceptor {
    return Interceptor {
        val original = it.request()

        val url = original.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        it.proceed(url)
    }
}


fun provideAuthRepository(sharedPrefs: SharedPrefs) =
    AuthRepository(sharedPrefs)

fun provideRepository(apiService: ApiService) =
    Repository(apiService)

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


fun provideApiServiceWithAuth(okHttpClient: OkHttpClient, baseUrl: String) : ApiService {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}

fun provideApiServiceWithoutAuth(okHttpClient: OkHttpClient, baseUrl: String) : ApiService {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}