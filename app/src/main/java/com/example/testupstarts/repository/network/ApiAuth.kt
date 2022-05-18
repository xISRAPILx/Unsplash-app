package com.example.testupstarts.repository.network

import com.example.testupstarts.repository.models.AuthToken
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiAuth {
    @POST("/oauth/token")
    suspend fun getToken(
        @Query("code") code: String
    ) : AuthToken
}