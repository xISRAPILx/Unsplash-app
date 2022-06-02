package com.example.unsplashapp.di

import com.example.unsplashapp.AuthInteractor
import com.example.unsplashapp.BuildConfig
import com.example.unsplashapp.Interceptor
import com.example.unsplashapp.repository.ApiPhoto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitModule(authInteractor: AuthInteractor){
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
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiClient : ApiPhoto by lazy {
        retrofit.create(ApiPhoto::class.java)
    }
}