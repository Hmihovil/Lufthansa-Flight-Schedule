package com.example.lufthansa_soft

import android.app.Application
import com.example.lufthansa_soft.di.appModule
import com.example.lufthansa_soft.utils.SharedPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    companion object {
        lateinit var pref: SharedPrefs
    }

    override fun onCreate() {
        super.onCreate()

        pref = SharedPrefs(applicationContext)
        pref.token = ""


        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MyApplication)

            // load properties from assets/koin.properties file

            // module list
            modules(appModule)
        }
    }
}