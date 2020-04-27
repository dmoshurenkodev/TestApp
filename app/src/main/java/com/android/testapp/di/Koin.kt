package com.android.testapp.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Koin {
    companion object {
        fun startKoin(appContext: Context) {
            startKoin {
                androidContext(appContext)
                modules(
                    listOf(
                        dataSourceModule,
                        repositoryModule,
                        viewModelModule,
                        mapperModule,
                        interactorsModule
                    )
                )
            }
        }
    }
}