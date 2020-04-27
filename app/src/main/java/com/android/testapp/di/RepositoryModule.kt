package com.android.testapp.di

import com.android.testapp.data.repository.CornRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CornRepository(get()) }
}