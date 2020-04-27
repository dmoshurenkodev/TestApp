package com.android.testapp.di

import com.android.testapp.BuildConfig
import com.android.testapp.data.network.ApiService
import com.android.testapp.data.network.datasource.NetworkDataSource
import com.android.testapp.data.network.datasource.NetworkDataSourceImpl
import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataSourceModule = module {
    single { createOkHttpClient() }
    single { createApiRestService<ApiService>(get()) }
    single<NetworkDataSource> { NetworkDataSourceImpl(get(), get()) }
}

private fun createOkHttpClient(): OkHttpClient {

    val okHttpBuilder = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLogger)
    }
    okHttpBuilder.addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder().apply {
            addHeader("DeviceID", BuildConfig.DEVICE_ID)
        }
        chain.proceed(requestBuilder.build())
    }

    return okHttpBuilder.build()
}

inline fun <reified T> createApiRestService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()

    return retrofit.create(T::class.java)
}
