package com.example.testupstarts.repository

import com.example.testupstarts.BuildConfig

class PhotoRepoImpl(private val api: ApiPhoto) : PhotoRepository {
    override suspend fun getPhotos(): List<Photo> {
        return api.getPhotos(BuildConfig.ID_OR_SLUG,1,30,"portrait")
    }

    override suspend fun likeAPhoto(idPhoto: String) {
        api.likeAPhoto(idPhoto)
    }

    override suspend fun unlikeAPhoto(idPhoto: String) {
        api.unlikeAPhoto(idPhoto)
    }
}