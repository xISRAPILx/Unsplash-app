package com.example.testupstarts

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original : Request = chain
            .request()
        val originalHttpUrl : HttpUrl = original.url

        val url : HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("client_id", BuildConfig.CLIENT_ID)
            .build()

        val requestBuilder : Request.Builder = original.newBuilder()
            .url(url)

        val request : Request = requestBuilder
            .build()

        return chain.proceed(request)
    }
}