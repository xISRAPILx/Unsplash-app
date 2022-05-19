package com.example.testupstarts.repository.models

data class AuthToken(
    val access_token: String? = null,
    val token_type: String? = null,
    val refresh_token: String? = null,
    val scope: String? = null
)