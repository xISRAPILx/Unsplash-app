package com.example.testupstarts.repository.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @field:Json(name = "username")
    var username: String? = null,

    @field:Json(name = "instagram_username")
    var instagramUsername: String? = null,

    @field:Json(name = "name")
    var name: String? = null,

    @field:Json(name = "first_name")
    var first_name: String? = null,

    @field:Json(name = "last_name")
    var last_name: String? = null
) : Parcelable