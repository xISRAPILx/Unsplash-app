package com.example.testupstarts.repository.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String? = null,
    @SerializedName("description") val desc: String? = null,
    @SerializedName("urls") val imageUrls: Urls? = null,
    @SerializedName("user") val author: User? = null,
    val likes: Int? = null,
    val liked_by_user: Boolean? = null
) : Parcelable  //todo нехорошо