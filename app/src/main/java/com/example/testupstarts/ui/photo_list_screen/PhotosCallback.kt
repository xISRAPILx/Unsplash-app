package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.repository.models.PhotoItem

interface PhotosCallback {
    fun onItemClick(photo: PhotoItem)
    fun onLikeClick(like: Boolean, photo: PhotoItem)
}