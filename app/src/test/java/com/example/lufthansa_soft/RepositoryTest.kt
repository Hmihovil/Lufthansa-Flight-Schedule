package com.example.lufthansa_soft

import android.content.Context
import com.example.lufthansa_soft.di.appModule
import com.example.lufthansa_soft.model.TokenResponse
import com.example.lufthansa_soft.repository.Repository
import com.example.lufthansa_soft.utils.Constants
import com.example.lufthansa_soft.viewModel.SharedViewModel
import com.nhaarman.mockitokotlin2.isNotNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


class RepositoryTest: KoinTest {

    private val repository: Repository by inject()

    @Before
    fun before() {
        startKoin{
            modules(appModule)
        }
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun verifyGetTokenIsCalled() {
//        whenever(repository.getToken(
//            Constants.CLIENT_ID,
//            Constants.CLIENT_SECRET,
//            Constants.GRANT_TYPE)).thenReturn(Single.just(TokenResponse()))
////
//        repository.getToken(Constants.CLIENT_ID,
//            Constants.CLIENT_SECRET,
//            Constants.GRANT_TYPE).test().assertNoErrors()
//            .assertValue(TokenResponse(isNotNull(), isNotNull(), isNotNull()))

    }
}