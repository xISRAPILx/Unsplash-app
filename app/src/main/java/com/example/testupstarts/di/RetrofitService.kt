package com.example.testupstarts.di

import com.example.testupstarts.AuthInteractor
import com.example.testupstarts.BuildConfig
import com.example.testupstarts.Interceptor
import com.example.testupstarts.repository.ApiPhoto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService(authInteractor: AuthInteractor){
    private val loggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val interceptor = Interceptor(authInteractor)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiClient : ApiPhoto by lazy {
        retrofit.create(ApiPhoto::class.java)
    }
}