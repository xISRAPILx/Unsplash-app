package com.example.testupstarts.repository.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Photo(
    val id: String? = null,

    @field:Json(name = "description")
    val desc: String? = null,

    @field:Json(name = "urls")
    val imageUrls: Urls? = null,

    @field:Json(name = "user")
    val author: User? = null,

    val likes: Int? = null,

    //todo уточнить нужны или арннотации
    //не нужны
    val liked_by_user: Boolean? = null
) : Parcelable  //todo нехорошо

//todo разнесьти все классы по разным файлам
//сделано