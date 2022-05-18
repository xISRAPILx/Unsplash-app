package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.repository.models.PhotosItem

interface PhotosCallback {
    fun onItemClick(photo: PhotosItem)
    fun onLikeClick(like: Boolean, photo: PhotosItem)
}