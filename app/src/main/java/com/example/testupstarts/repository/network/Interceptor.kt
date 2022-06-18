package com.example.testupstarts.repository.network

import com.example.testupstarts.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor: Interceptor {
    override fun intercept(chain: okhttp3.Interceptor.Chain): Response {
        val original : Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url : HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("client_id", BuildConfig.CLIENT_ID)
            .addQueryParameter("client_secret", BuildConfig.CLIENT_SECRET)
            .addQueryParameter("redirect_uri", BuildConfig.REDIRECT_URI)
            .addQueryParameter("grant_type", BuildConfig.GRANT_TYPE)
            .build()
        val requestBuilder : Request.Builder = original.newBuilder()
            .url(url)
        val request : Request = requestBuilder.build()
        return chain.proceed(request)
    }

}