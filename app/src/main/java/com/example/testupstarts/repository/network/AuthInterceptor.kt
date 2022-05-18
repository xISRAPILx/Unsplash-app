package com.example.testupstarts.repository.network

import com.example.testupstarts.ui.auth_screen.AuthInteractor
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authInteractor: AuthInteractor): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain
            .request()
        if (authInteractor.getToken() != null) {
            val newRequest = original.newBuilder()
                .addHeader(AUTHORIZATION_KEY,  String.format("Bearer %s", authInteractor.getToken()))
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(original)
    }
    companion object {
        const val AUTHORIZATION_KEY = "Authorization"
    }
}