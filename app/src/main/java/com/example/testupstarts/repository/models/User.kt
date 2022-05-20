package com.example.testupstarts.repository.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("username") var username: String? = null,
    @SerializedName("instagram_username") var instagramUsername: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("first_name") var first_name: String? = null,
    @SerializedName("last_name") var last_name: String? = null
) : Parcelable