package com.example.unsplashapp.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cache")
data class PhotosItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "desc")
    val desc: String? = null,

    @ColumnInfo(name = "imageUrlRegular")
    val imageUrlRegular: String,

    @ColumnInfo(name = "imageUrlSmall")
    val imageUrlSmall: String,

    @ColumnInfo(name = "authorUserName")
    val authorUserName: String,

    @ColumnInfo(name = "likes")
    val likes: Int? = null,

    @ColumnInfo(name = "authorName")
    val authorName: String? = null,

    @ColumnInfo(name = "instagramUsername")
    val instagramUsername: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
): Parcelable
