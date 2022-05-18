package com.example.testupstarts

import android.util.Log
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor(private val authInteractor: AuthInteractor): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original : Request = chain
            .request()
        if (authInteractor.getTokenFromPrefs() != null) {
            original = original.newBuilder()
                .addHeader(AUTHORIZATION_KEY,  String.format("Bearer %s", authInteractor.getTokenFromPrefs()))
                .build()
            Log.e("header", original.toString())
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
    companion object {
        const val AUTHORIZATION_KEY = "Authorization"
    }
}