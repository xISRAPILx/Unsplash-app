package com.example.testupstarts.di

import com.example.testupstarts.repository.network.AppAuthInterceptor
import com.example.testupstarts.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AuthRetrofitService {
    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val interceptor = AppAuthInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_AUTH)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiClient: ApiAuth by lazy {
        retrofit.create(ApiAuth::class.java)
    }
}