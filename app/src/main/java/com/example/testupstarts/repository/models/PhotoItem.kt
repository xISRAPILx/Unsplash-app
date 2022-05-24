package com.example.testupstarts.repository.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "photoCache") //todo апридуммать норм имя
data class PhotosItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "desc")
    val desc: String? = null,

    @ColumnInfo(name = "imageUrlRegular")
    val imageUrlRegular: String? = null,

    @ColumnInfo(name = "imageUrlSmall")
    val imageUrlSmall: String? = null,

    @ColumnInfo(name = "authorUserName")
    val authorUserName: String? = null,

    @ColumnInfo(name = "likes")
    val likes: Int? = null,

    @ColumnInfo(name = "authorName")
    val authorName: String? = null,

    @ColumnInfo(name = "instagramUsername")
    val instagramUsername: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean? = null
): Parcelable
