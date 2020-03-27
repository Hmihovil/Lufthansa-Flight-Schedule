package com.example.lufthansa_soft

import com.example.lufthansa_soft.di.appModule
import com.example.lufthansa_soft.network.ApiService
import com.example.lufthansa_soft.repository.Repository
import com.example.lufthansa_soft.utils.Constants
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.inject

class RepositoryTest: KoinTest {

    private val repository: Repository by inject()

    @Before
    fun before() {
        startKoin{listOf(appModule)}.checkModules()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun verifyGetTokenIsCalled() {
//        when(repository.getToken(
//            Constants.CLIENT_ID,
//            Constants.CLIENT_SECRET,
//            Constants.GRANT_TYPE))


    }
}