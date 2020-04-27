package com.android.testapp.di

import com.android.testapp.domain.interactor.GetCornListInteractor
import org.koin.dsl.module

val interactorsModule = module {
    factory { GetCornListInteractor(get()) }
}