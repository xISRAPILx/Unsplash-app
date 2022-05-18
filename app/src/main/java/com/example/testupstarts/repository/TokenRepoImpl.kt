package com.example.testupstarts.repository

import com.example.testupstarts.repository.models.AuthToken
import com.example.testupstarts.repository.network.ApiPhoto

class TokenRepoImpl(private val api: ApiPhoto):TokenRepository {
    override suspend fun getToken(code: String): AuthToken {
        return api.getToken(code)
    }
}