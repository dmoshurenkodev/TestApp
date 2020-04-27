package com.android.testapp.di

import com.android.testapp.presentation.corns.CornsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CornsViewModel(get()) }
}