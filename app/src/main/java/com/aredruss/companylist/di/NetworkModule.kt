package com.aredruss.companylist.di

import com.aredruss.companylist.domain.CompanyInteractor.Companion.BASE_URL_TEST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val interceptorLogger = HttpLoggingInterceptor()
        interceptorLogger.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptorLogger)
            .build()

        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL_TEST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}