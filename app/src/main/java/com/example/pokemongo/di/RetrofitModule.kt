package com.example.pokemongo.di

import com.example.pokemongo.BuildConfig
import com.example.pokemongo.common.config.ApiConfig
import com.example.pokemongo.data.api.RetrofitInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { initRetrofitInterface() }
}

private fun initRetrofitInterface() = initRetrofit().create(RetrofitInterface::class.java)

private fun initRetrofit(): Retrofit {
    val okHttpClient = initOkHttpClient().build()
    return Retrofit.Builder()
        .baseUrl(ApiConfig.DOMAIN)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun initOkHttpClient(): OkHttpClient.Builder {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(ApiConfig.TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(ApiConfig.TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(ApiConfig.TIMEOUT, TimeUnit.SECONDS)
}