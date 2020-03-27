package com.example.lufthansa_soft

import com.example.lufthansa_soft.di.appModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class DependecyGraphTest: KoinTest {

    @Test
    fun checkDependencyGraph() {
        startKoin{listOf(appModule)}.checkModules()
        stopKoin()
    }
}