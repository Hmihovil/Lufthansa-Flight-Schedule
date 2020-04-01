package com.example.lufthansa_soft

import android.app.Application
import android.content.Context
import com.example.lufthansa_soft.di.appModule
import com.example.lufthansa_soft.model.TokenResponse
import com.example.lufthansa_soft.network.ApiService
import com.example.lufthansa_soft.repository.Repository
import com.example.lufthansa_soft.utils.Constants
import com.example.lufthansa_soft.viewModel.SharedViewModel
import com.nhaarman.mockitokotlin2.isNotNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock

class RepositoryTest: KoinTest, Application() {

    private val repository: Repository by inject()
    private val apiService: ApiService by inject()
//    private lateinit var repository: Repository
    private val context = mock(Context::class.java)

    @Rule @JvmField var testSchedulerRule = RxSchedulerTestRule()

    @Before
    fun before() {
        startKoin{
            androidContext(context)
            modules(appModule)
        }
//        repository = Repository(apiService)
    }


    @After
    fun after() {
        stopKoin()
    }



    @Test
    fun verifyGetTokenIsCalled() {
        val tokenResponse = TokenResponse(
            accessToken = "8hsbau84wakaav65", tokenType = "bearer", expiresIn = 129600)
        whenever(repository.getToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)).thenReturn(Single.just(tokenResponse))

        val testObserver = repository.getToken(Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE).test()

        testObserver.assertNoErrors()
            .assertValue { c -> c.accessToken == "8hsbau84wakaav65" }

    }
}