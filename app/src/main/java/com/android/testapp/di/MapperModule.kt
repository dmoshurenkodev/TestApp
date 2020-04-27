package com.android.testapp.di
import com.android.testapp.data.network.mapper.CornFromApiMapper
import org.koin.dsl.module

val mapperModule = module{
    factory { CornFromApiMapper() }
}