package com.example.swipebhanu

import android.app.Application

import com.example.swipebhanu.direct.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }


}