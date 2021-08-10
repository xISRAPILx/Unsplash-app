package com.example.testupstarts

import com.example.testupstarts.repository.PhotosItem

interface PhotosCallback {
    fun onItemClick(photo: PhotosItem)

    fun onLikeClick(like: Boolean, photo: PhotosItem)
}