package com.example.testupstarts.repository

import com.example.testupstarts.repository.models.AuthToken

interface TokenRepository {
    suspend fun getToken(code:String): AuthToken
}