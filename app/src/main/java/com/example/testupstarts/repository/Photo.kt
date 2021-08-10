package com.example.testupstarts.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id")
    val id: String,

    @SerializedName("description")
    val desc: String? = null,

    @SerializedName("urls")
    val imageUrls: Urls,

    @SerializedName("user")
    val author: User,

    @SerializedName("likes")
    val likes: Int
) : Parcelable

@Parcelize
data class User(
    @SerializedName("username")
    var username: String,

    @SerializedName("instagram_username")
    var instagramUsername: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("first_name")
    var first_name: String? = null,

    @SerializedName("last_name")
    var last_name: String? = null
) : Parcelable

@Parcelize
data class Urls(
    @SerializedName("raw")
    var raw: String,

    @SerializedName("full")
    var full: String,

    @SerializedName("regular")
    var regular: String,

    @SerializedName("small")
    var small: String,
) : Parcelable
