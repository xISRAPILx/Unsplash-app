package com.example.testupstarts.repository

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("access_token")
    val access_token: String,

    @SerializedName("token_type")
    val token_type: String,

    @SerializedName("refresh_token")
    val refresh_token: String,

    @SerializedName("scope")
    val scope: String
)