package com.example.testupstarts.repository.models

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("access_token") val accessToken: String? = null,
    @SerializedName("token_type")val tokenType: String? = null,
    @SerializedName("refresh_token")val refreshToken: String? = null,
    val scope: String? = null
)