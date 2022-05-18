package com.example.testupstarts.ui.photo_list_screen.repo

import com.example.testupstarts.repository.Photo

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>

    suspend fun likeAPhoto(idPhoto: String)

    suspend fun unlikeAPhoto(idPhoto: String)
}