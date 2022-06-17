package com.example.testupstarts.repository.network

import android.util.Log
import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val prefsRepo: PrefsRepository): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain
            .request()
        if (prefsRepo.getToken() != null) {
            Log.d("getToken", prefsRepo.getToken().toString())
            val newRequest = original.newBuilder()
                .addHeader(AUTHORIZATION_KEY,  String.format("Bearer %s", prefsRepo.getToken()))
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(original)
    }
    companion object {
        const val AUTHORIZATION_KEY = "Authorization"
    }
}