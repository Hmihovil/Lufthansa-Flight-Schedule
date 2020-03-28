package com.example.lufthansa_soft

import android.app.Application
import com.example.lufthansa_soft.di.appModule
import com.example.lufthansa_soft.utils.SharedPrefs
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

////    companion object {
////         val pref: SharedPrefs = SharedPrefs(applicationContext)
        val pref: SharedPrefs by inject()
////    }

    override fun onCreate() {
        super.onCreate()



        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MyApplication)
            androidFileProperties()

            // load properties from assets/koin.properties file

            // module list
            modules(appModule)
        }

        pref.token = ""

    }
}