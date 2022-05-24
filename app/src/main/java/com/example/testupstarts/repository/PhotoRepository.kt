package com.example.testupstarts.repository

import com.example.testupstarts.repository.models.Photo

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>

    suspend fun setLike(idPhoto: String, like: Boolean)
}