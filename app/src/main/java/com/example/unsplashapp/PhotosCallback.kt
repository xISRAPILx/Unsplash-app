package com.example.unsplashapp

import com.example.unsplashapp.repository.PhotosItem

interface PhotosCallback {
    fun onItemClick(photo: PhotosItem)
    fun onLikeClick(like: Boolean, photo: PhotosItem, photos: List<PhotosItem>)
}