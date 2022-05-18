package com.example.testupstarts.repository.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @field:Json(name = "id")
    val id: String,

    @field:Json(name = "description")
    val desc: String? = null,

    @field:Json(name = "urls")
    val imageUrls: Urls,

    @field:Json(name = "user")
    val author: User,

    @field:Json(name = "likes")
    val likes: Int,

    //todo уточнить нужны или арннотации
    @field:Json(name = "liked_by_user")
    val liked_by_user: Boolean
) : Parcelable  //todo нехорошо

@Parcelize
data class User(
    @field:Json(name = "username")
    var username: String,

    @field:Json(name = "instagram_username")
    var instagramUsername: String? = null,

    @field:Json(name = "name")
    var name: String? = null,

    @field:Json(name = "first_name")
    var first_name: String? = null,

    @field:Json(name = "last_name")
    var last_name: String? = null
) : Parcelable

@Parcelize
data class Urls(
    @field:Json(name = "raw")
    var raw: String,

    @field:Json(name = "full")
    var full: String,

    @field:Json(name = "regular")
    var regular: String,

    @field:Json(name = "small")
    var small: String,
) : Parcelable
//todo разнесьти все классы по разным файлам