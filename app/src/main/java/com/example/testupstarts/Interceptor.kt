package com.example.testupstarts

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor(private val authInteractor: AuthInteractor): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original : Request = chain
            .request()
        if (original.method == "POST" || original.method == "DELETE") {
            original = original.newBuilder()
                .addHeader("Authorization", "Bearer "+ authInteractor.getToken().toString())
                .build()
        }
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