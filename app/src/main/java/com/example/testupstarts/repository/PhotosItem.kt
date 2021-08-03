package com.example.testupstarts.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotosItem(
    val id: String,
    val desc: String? = null,
    val imageUrlRegular: String,
    val imageUrlSmall: String,
    val authorUserName: String,
    val likes: Int? = null,
    var authorName: String? = null,
    var instagramUsername: String? = null,
    var favorite: Boolean
): Parcelable
