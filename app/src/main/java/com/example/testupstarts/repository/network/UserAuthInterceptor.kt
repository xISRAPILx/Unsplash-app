package com.example.testupstarts.repository.network

import com.example.testupstarts.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class UserAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter(CLIENT_ID_QUERY, BuildConfig.CLIENT_ID)
            .addQueryParameter(CLIENT_SECRET_QUERY, BuildConfig.CLIENT_SECRET)
            .addQueryParameter(REDIRECT_URI_QUERY, BuildConfig.REDIRECT_URI)
            .addQueryParameter(GRANT_TYPE_QUERY, BuildConfig.GRANT_TYPE)
            .build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        const val CLIENT_ID_QUERY = "client_id"
        const val CLIENT_SECRET_QUERY = "client_secret"
        const val REDIRECT_URI_QUERY = "redirect_uri"
        const val GRANT_TYPE_QUERY = "grant_type"

    }
}