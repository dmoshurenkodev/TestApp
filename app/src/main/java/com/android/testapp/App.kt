package com.android.testapp

import android.app.Application
import com.android.testapp.di.Koin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Koin.startKoin(this)
    }
}