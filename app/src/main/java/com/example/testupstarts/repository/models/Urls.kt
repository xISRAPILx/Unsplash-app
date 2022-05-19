package com.example.testupstarts.repository.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Urls(
    @field:Json(name = "raw")
    var raw: String? = null,

    @field:Json(name = "full")
    var full: String? = null,

    @field:Json(name = "regular")
    var regular: String? = null,

    @field:Json(name = "small")
    var small: String? = null,
) : Parcelable
