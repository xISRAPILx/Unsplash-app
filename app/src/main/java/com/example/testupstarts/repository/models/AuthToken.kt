package com.example.testupstarts.repository.models

import com.squareup.moshi.Json

data class AuthToken(
    @field:Json(name = "access_token")
    val access_token: String,

    @field:Json(name = "token_type")
    val token_type: String,

    @field:Json(name = "refresh_token")
    val refresh_token: String,

    @field:Json(name = "scope")
    val scope: String
)