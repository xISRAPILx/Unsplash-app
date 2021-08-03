package com.example.testupstarts

import com.example.testupstarts.repository.PhotosItem

interface PhotosCallback {
    fun onItemClick(jeans: PhotosItem)

    fun onLikeClick(like: Boolean, id: String)
}