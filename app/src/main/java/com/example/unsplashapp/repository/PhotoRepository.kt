package com.example.unsplashapp.repository

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>

    suspend fun likeAPhoto(idPhoto: String)

    suspend fun unlikeAPhoto(idPhoto: String)
}